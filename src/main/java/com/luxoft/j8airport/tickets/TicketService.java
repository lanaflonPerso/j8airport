package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.flights.domain.Flight;

public interface TicketService
{
    Ticket calculateAndApplyDiscount(Ticket ticket);

    Ticket createTicket(Client client, Flight flight);
}
