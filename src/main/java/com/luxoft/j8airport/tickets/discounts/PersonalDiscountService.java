package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.clients.Status;
import com.luxoft.j8airport.tickets.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDiscountService implements DiscountService
{
    private TicketRepository ticketRepository;

    @Override
    public double getPersonalPrice(Client client, double price)
    {
        double moneySpent = ticketRepository.findAllByClient(client)
                .stream()
                .mapToDouble(t -> t.getPrice())
                .sum();

        return price * getDiscountMultiplier(moneySpent);
    }

    private double getDiscountMultiplier(double moneySpent)
    {
        double multiplier = 1.0;

        if (moneySpent >= Status.PLATINUM.getMoneySpentLimit())
        {
            multiplier = 0.85;
        }
        else if (moneySpent >= Status.GOLD.getMoneySpentLimit())
        {
            multiplier = 0.9;
        }
        else if (moneySpent >= Status.SILVER.getMoneySpentLimit())
        {
            multiplier = 0.95;
        }

        return multiplier;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository)
    {
        this.ticketRepository = ticketRepository;
    }
}
