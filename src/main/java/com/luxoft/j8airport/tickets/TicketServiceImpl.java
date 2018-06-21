package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.tickets.discounts.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService
{
    @Autowired
    private DiscountService discountService;

    @Override
    public Ticket calculateAndApplyDiscount(Ticket ticket)
    {
        double priceBefore = ticket.getPrice();
        double priceAfter = discountService.getPersonalPrice(ticket.getClient(), ticket.getPrice());

        ticket.setPrice(priceAfter);
        ticket.setDiscount(100 - (priceAfter / priceBefore) * 100);

        return ticket;
    }
}
