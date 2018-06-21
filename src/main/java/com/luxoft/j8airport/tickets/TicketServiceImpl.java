package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.flights.domain.Flight;
import com.luxoft.j8airport.tickets.discounts.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService
{
    @Autowired
    private DiscountService discountService;

    @Autowired
    private TicketRepository repository;

    @Override
    public Ticket calculateAndApplyDiscount(Ticket ticket)
    {
        double priceBefore = ticket.getPrice();
        double priceAfter = discountService.getPersonalPrice(ticket.getClient(), ticket.getPrice());

        ticket.setPrice(priceAfter);
        ticket.setDiscount(100 - (priceAfter / priceBefore) * 100);

        return ticket;
    }

    @Override
    public Ticket createTicket(Client client, Flight flight)
    {
        Ticket ticket = new Ticket();

        ticket.setClient(client);
        ticket.setFlight(flight);
        ticket.setPrice(flight.getFlightCard().getDistance() * 0.92);

        ticket = calculateAndApplyDiscount(ticket);

        return repository.save(ticket);
    }
}
