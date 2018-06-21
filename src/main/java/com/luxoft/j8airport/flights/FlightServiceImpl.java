package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.clients.Status;
import com.luxoft.j8airport.flights.domain.Airport;
import com.luxoft.j8airport.flights.domain.Flight;
import com.luxoft.j8airport.flights.domain.FlightCard;
import com.luxoft.j8airport.tickets.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService
{
    private static final int MIN_CLIENTS_TO_FLY = 1;

    private Map<Long, Flight> waitingForClients = new ConcurrentHashMap<>(10);

    private static final AtomicLong gen = new AtomicLong(0);

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void setUpFlight(FlightCard card)
    {
        CompletableFuture
                .supplyAsync(() -> new Flight(card))
                .thenAccept(this::setupFlight);

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
                .filter(flight -> flight.getFlightCard().getTo().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAvailableFlightsFrom(String city)
    {
        return null;
    }

    @Override
    public List<Flight> getAvailableFlights(String from, String to)
    {
        return null;
    }

    private void setupFlight(Flight flight)
    {
        flight.setId(gen.incrementAndGet());

        ZonedDateTime departure = ZonedDateTime
                .now(ZoneId.of(flight.getFlightCard().getFrom().getZoneId()))
                .withHour(14)
                .withMinute(0)
                .withSecond(0);

        ZonedDateTime arrive = ZonedDateTime.ofInstant(

                    departure.plusMinutes(flight.getFlightCard().getFlightTimeObj().toMinutes()).toInstant(),
                    ZoneId.of(flight.getFlightCard().getTo().getZoneId())
        );

        flight.setDeparture(departure);
        flight.setArrive(arrive);

        flight.startBoarding();
        waitingForClients.put(flight.getId(), flight);
    }

    @Override
    public void loadFlights()
    {
        Airport amster = new Airport();
        amster.setId(0L);
        amster.setCountry("Netherlands");
        amster.setCity("Amsterdam");
        amster.setName("Schiphol");
        amster.setZoneId("Europe/Amsterdam");

        Airport kiev = new Airport();
        kiev.setId(1L);
        kiev.setCountry("Ukraine");
        kiev.setCity("Kiev");
        kiev.setName("Borispol");
        kiev.setZoneId("Europe/Kiev");

        Airport london = new Airport();
        london.setId(2L);
        london.setCountry("UK");
        london.setCity("London");
        london.setName("Heathrow");
        london.setZoneId("Europe/London");


        FlightCard ka = new FlightCard();
        ka.setFrom(kiev);
        ka.setTo(amster);
        ka.setFlightTime(Duration.ofHours(3));
        ka.setDistance(1900);
        ka.setMaxPassengers(189);

        FlightCard ak = new FlightCard();
        ak.setFrom(amster);
        ak.setTo(kiev);
        ak.setFlightTime(Duration.ofMinutes(185));
        ak.setDistance(1900);
        ak.setMaxPassengers(189);


        FlightCard kl = new FlightCard();
        kl.setFrom(kiev);
        kl.setTo(london);
        kl.setFlightTime(Duration.ofMinutes(205));
        kl.setDistance(2400);
        kl.setMaxPassengers(189);

        FlightCard lk = new FlightCard();
        lk.setFrom(kiev);
        lk.setTo(london);
        lk.setFlightTime(Duration.ofMinutes(195));
        lk.setDistance(2400);
        lk.setMaxPassengers(189);

        setUpFlight(ka);
        setUpFlight(ak);
        setUpFlight(kl);
        setUpFlight(lk);
    }
}
