package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.tickets.discounts.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Double getAverageMoneySpent()
    {

        return getClientToSpending().entrySet()
                .stream()
                .mapToDouble(e -> e.getValue())
                .average().getAsDouble();
    }

    @Override
    public Set<Client> getAllAboveAverageSpenders()
    {
        double averageMoneySpent = getAverageMoneySpent();

        return getClientToSpending()
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > averageMoneySpent)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    private Map<Client, Double> getClientToSpending()
    {
        return repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getClient(),
                        Collectors.averagingDouble(t -> t.getPrice())));
    }


    @Override
    public Set<Client> getAllClientsAbove(int flightLimit)
    {
        return getClientToTickets().entrySet()
                .stream()
                .filter(e -> e.getValue() > flightLimit)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    private Map<Client, Long> getClientToTickets()
    {
        return repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getClient(), Collectors.counting()));
    }


}
