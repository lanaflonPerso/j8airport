package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.domain.Status;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.tickets.TicketRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightsServiceTests
{

    @Autowired
    private FlightService flightService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientSupportService clientSupportService;

    @Autowired
    private TicketRepository ticketRepository;

    private Flight flight;

    @Before
    public void setupFlight()
    {
        System.out.println("setupFlight --> ");

        Long flightId = flightService.getAllAvailableFlights().get(1).getId();

        List<Client> platinumClients = clientSupportService.generateAndStoreClients(Status.PLATINUM, 1);
        List<Client> goldClients = clientSupportService.generateAndStoreClients(Status.GOLD, 3);
        List<Client> silverClients = clientSupportService.generateAndStoreClients(Status.SILVER, 2);
        List<Client> noneClients = clientSupportService.generateAndStoreClients(Status.NONE, 6);

        List<Client> clients = new ArrayList<>();

        clients.addAll(platinumClients);
        clients.addAll(goldClients);
        clients.addAll(silverClients);
        clients.addAll(noneClients);

        for (Client client : clients)
        {
            flightService.buyTicket(client.getId(), flightId);
        }

        flight = flightService.findById(flightId);
    }

    @After
    public void cleanDB()
    {

        ticketRepository.deleteAll();

        clientSupportService.deleteAllGeneratedClients();

        flight.setTicketsBought(new ArrayList<>());
    }

    @Test
    public void flightsShouldBeGeneratedTest()
    {
        List<Flight> flights = flightService.getAllAvailableFlights();

        assertTrue("--> flightsShouldBeGeneratedTest", flights.size() > 0);
    }

    @Test
    public void buyTicketTest1()
    {
        Client client = clientService.findAll().get(0);

        Ticket ticket = flightService.buyTicket(client.getId(), flight.getId());

        assertNotNull("--> ticket should be stored and contain id ", ticket.getId());
    }

    @Test
    public void buyTicketTest2()
    {
        Client client = clientService.findAll().get(0);

        Ticket ticket = flightService.buyTicket(client.getId(), flight.getId());

        flight = flightService.findById(flight.getId());

        System.out.println("--> " + flight.getTicketsBought().size());
        assertTrue("--> flight should contain stored ticket ", flight.getTicketsBought().contains(ticket));
    }

    @Test
    public void getAvailableFlightsToTest1()
    {
        String city = "Kiev";
        int expectedFlightsCount = 2;

        List<Flight> availableFlights = flightService.getAvailableFlightsTo(city);

        assertEquals("should be " + expectedFlightsCount + " available flights to " + city,
                expectedFlightsCount, availableFlights.size());
    }

    @Test
    public void getAvailableFlightsFromTest()
    {
        String city = "Kiev";
        int expectedFlightsCount = 2;

        List<Flight> availableFlights = flightService.getAvailableFlightsFrom(city);

        assertEquals("should be " + expectedFlightsCount + " available flights from " + city,
                expectedFlightsCount, availableFlights.size());
    }

    @Test
    public void getAvailableFlightsTest()
    {
        String from = "Kiev";
        String to = "London";
        int expectedFlightsCount = 1;

        List<Flight> availableFlights = flightService.getAvailableFlights(from, to);

        assertEquals("should be " + expectedFlightsCount + " available flights from " + from + " to " + to,
                expectedFlightsCount, availableFlights.size());

    }

    @Test
    public void getPassengersWithTest1()
    {
        List<Client> passengers = flightService.getPassengersWith(flight, Status.PLATINUM);

        int expectedPlatinumPassengersCount = 1;

        assertEquals("should be " + expectedPlatinumPassengersCount + " PLATINUM passengers",
                expectedPlatinumPassengersCount, passengers.size());
    }

    @Test
    public void getPassengersWithTest2()
    {
        List<Client> passengers = flightService.getPassengersWith(flight, Status.GOLD);

        int expectedGoldPassengersCount = 3;

        assertEquals("should be " + expectedGoldPassengersCount + " GOLD passengers",
                expectedGoldPassengersCount, passengers.size());
    }

    @Test
    public void getPassengersWithTest3()
    {
        List<Client> passengers = flightService.getPassengersWith(flight, Status.SILVER);

        int expectedSilverPassengersCount = 2;

        assertEquals("should be " + expectedSilverPassengersCount + " SILVER passengers",
                expectedSilverPassengersCount, passengers.size());
    }

    @Test
    public void getPassengersWithTest4()
    {
        List<Client> passengers = flightService.getPassengersWith(flight, Status.NONE);

        int expectedNoneStatusPassengersCount = 6;

        assertEquals("should be " + expectedNoneStatusPassengersCount + " NONE status passengers",
                expectedNoneStatusPassengersCount, passengers.size());
    }

    @Test
    public void getPassengersGroupedByStatusTest1()
    {
        Map<Status, Set<Client>> passengersByStatus = flightService.getPassengersGroupedByStatus(flight);

        int expectedPlatinumPassengersCount = 1;

        assertEquals("should be " + expectedPlatinumPassengersCount + " PLATINUM passengers",
                expectedPlatinumPassengersCount, passengersByStatus.get(Status.PLATINUM).size());
    }

    @Test
    public void getPassengersGroupedByStatusTest2()
    {
        Map<Status, Set<Client>> passengersByStatus = flightService.getPassengersGroupedByStatus(flight);

        int expectedGoldPassengersCount = 3;

        assertEquals("should be " + expectedGoldPassengersCount + " GOLD passengers",
                expectedGoldPassengersCount, passengersByStatus.get(Status.GOLD).size());
    }

    @Test
    public void getPassengersGroupedByStatusTest3()
    {
        Map<Status, Set<Client>> passengersByStatus = flightService.getPassengersGroupedByStatus(flight);

        int expectedSilverPassengersCount = 2;

        assertEquals("should be " + expectedSilverPassengersCount + " SILVER passengers",
                expectedSilverPassengersCount, passengersByStatus.get(Status.SILVER).size());
    }

    @Test
    public void getPassengersGroupedByStatusTest4()
    {
        Map<Status, Set<Client>> passengersByStatus = flightService.getPassengersGroupedByStatus(flight);

        int expectedNoneStatusPassengersCount = 6;

        assertEquals("should be " + expectedNoneStatusPassengersCount + " NONE passengers",
                expectedNoneStatusPassengersCount, passengersByStatus.get(Status.NONE).size());
    }

}
