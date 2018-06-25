package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.flights.domain.Flight;
import com.luxoft.j8airport.tickets.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightsServiceTests
{

    @Autowired
    private FlightService flightService;

    @Autowired
    private ClientService clientService;

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
        Flight flight = flightService.getAllAvailableFlights().get(0);

        Ticket ticket = flightService.buyTicket(client.getId(), flight.getId());

        assertNotNull("--> ticket should be stored and contain id ", ticket.getId());
    }

    @Test
    public void buyTicketTest2()
    {
        Client client = clientService.findAll().get(0);
        Flight flight = flightService.getAllAvailableFlights().get(0);

        Ticket ticket = flightService.buyTicket(client.getId(), flight.getId());

        flight = flightService.findById(flight.getId());

        assertTrue("--> flight should contain stored ticket ", ticket.getId() == flight.getTicketsBought().get(0).getId());
    }


}
