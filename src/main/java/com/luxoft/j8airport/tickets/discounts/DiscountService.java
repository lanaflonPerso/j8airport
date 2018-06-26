package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.tickets.TicketRepository;

public interface DiscountService
{
    /**
     *
     * @param client
     * @param price price before discount
     *
     * @return price according to the @{@link com.luxoft.j8airport.domain.Status} of this client
     */
    double getPersonalPrice(Client client, double price);

    void setTicketRepository(TicketRepository ticketRepository);
}
