package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.domain.Status;
import com.luxoft.j8airport.domain.Airport;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.FlightCard;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.tickets.TicketRepository;
import com.luxoft.j8airport.tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService
{
    private static final int MIN_CLIENTS_TO_FLY = 1;

    private Map<Long, Flight> waitingForClients = new ConcurrentHashMap<>(10);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightCardRepository flightCardRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ClientService clientService;

    @Override
    public void setUpFlight(FlightCard card)
    {
        Flight flight = new Flight(card);
        setUpFlight(flight);
    }

    @Override
    public Flight findById(Long flightId)
    {
        return waitingForClients.get(flightId);
    }

    @Override
    public Ticket buyTicket(Long clientId, Long flightId)
    {

        Client client = clientService.findById(clientId);
        Flight flight = findById(flightId);


        Ticket ticket = ticketService.createTicket(client, flight);

        flight.addTicket(ticket);

        flightRepository.save(flight);

        return ticket;
    }

    @Override
    public List<Flight> getAllAvailableFlights()
    {
        return new ArrayList<>(waitingForClients.values());
    }

    @Override
    public Map<Status, Set<Client>> getPassengersGroupedByStatus(Flight flight)
    {
        return ticketRepository.findAllByFlight(flight)
                .stream()
                .map(t -> t.getClient())
                .collect(Collectors.groupingBy((Client c) -> c.getStatus(), Collectors.toSet()));

    }

    @Override
    public List<Client> getPassengersWith(Flight flight, Status status)
    {
        return ticketRepository.findAllByFlight(flight)
                .stream()
                .map(t -> t.getClient())
                .filter(c -> status == c.getStatus())
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAvailableFlightsTo(String city)
    {
        return waitingForClients.values()
                .stream()
                .filter(flight -> flight.getFlightCard().getTo().getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAvailableFlightsFrom(String city)
    {
        return waitingForClients.values()
                .stream()
                .filter(flight -> flight.getFlightCard().getFrom().getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAvailableFlights(String from, String to)
    {
        return waitingForClients.values()
                .stream()
                .filter(flight -> flight.getFlightCard().getFrom().getCity().equals(from))
                .filter(flight -> flight.getFlightCard().getTo().getCity().equals(to))
                .collect(Collectors.toList());
    }

    private void setUpFlight(Flight flight)
    {
        ZonedDateTime departure = ZonedDateTime
                .now(ZoneId.of(flight.getFlightCard().getFrom().getZoneId()))
                .withHour(14)
                .withMinute(0)
                .withSecond(0);

        ZonedDateTime arrive =
                TimezoneUtils.datePlusDuration(
                    departure,
                    flight.getFlightCard().getFlightTimeObj(),
                    flight.getFlightCard().getTo().getZoneId());

        flight.setDeparture(departure);
        flight.setArrive(arrive);

        flight.startBoarding();

        Flight stored = flightRepository.save(flight);

        waitingForClients.put(stored.getId(), stored);
    }

    @Override
    public void loadFlights()
    {
        if (waitingForClients.size() > 1)
        {
            return;
        }

        Airport amster = new Airport();
        amster.setCountry("Netherlands");
        amster.setCity("Amsterdam");
        amster.setName("Schiphol");
        amster.setZoneId("Europe/Amsterdam");

        amster = airportRepository.save(amster);

        Airport kiev = new Airport();
        kiev.setCountry("Ukraine");
        kiev.setCity("Kiev");
        kiev.setName("Borispol");
        kiev.setZoneId("Europe/Kiev");

        kiev = airportRepository.save(kiev);

        Airport london = new Airport();
        london.setCountry("UK");
        london.setCity("London");
        london.setName("Heathrow");
        london.setZoneId("Europe/London");

        london = airportRepository.save(london);


        FlightCard ka = new FlightCard();
        ka.setFrom(kiev);
        ka.setTo(amster);
        ka.setFlightTime(Duration.ofHours(3));
        ka.setDistance(1900);
        ka.setMaxPassengers(189);

        ka = flightCardRepository.save(ka);

        FlightCard ak = new FlightCard();
        ak.setFrom(amster);
        ak.setTo(kiev);
        ak.setFlightTime(Duration.ofMinutes(185));
        ak.setDistance(1900);
        ak.setMaxPassengers(189);

        ak = flightCardRepository.save(ak);


        FlightCard kl = new FlightCard();
        kl.setFrom(kiev);
        kl.setTo(london);
        kl.setFlightTime(Duration.ofMinutes(205));
        kl.setDistance(2400);
        kl.setMaxPassengers(189);

        kl = flightCardRepository.save(kl);

        FlightCard lk = new FlightCard();
        lk.setFrom(london);
        lk.setTo(kiev);
        lk.setFlightTime(Duration.ofMinutes(195));
        lk.setDistance(2400);
        lk.setMaxPassengers(189);

        lk = flightCardRepository.save(lk);

        setUpFlight(ka);
        setUpFlight(ak);
        setUpFlight(kl);
        setUpFlight(lk);
    }
}
