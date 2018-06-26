package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.flights.FlightService;
import com.luxoft.j8airport.domain.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTests
{

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FlightService flightService;

    @Test
    public void createTicketTest()
    {
        Client client = clientService.findAll().get(0);
        Flight flight = flightService.getAllAvailableFlights().get(0);

        Ticket ticket = ticketService.createTicket(client, flight);

        assertNotNull("--> ticket should be stored and contain id ", ticket.getId());
    }

}
