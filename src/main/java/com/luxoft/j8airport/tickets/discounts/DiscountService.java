package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.tickets.TicketRepository;

public interface DiscountService
{
    double getPersonalPrice(Client client, double price);

    void setTicketRepository(TicketRepository ticketRepository);
}
