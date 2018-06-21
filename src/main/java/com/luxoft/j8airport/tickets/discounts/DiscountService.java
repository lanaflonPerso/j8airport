package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.clients.Client;

public interface DiscountService
{
    double getPersonalPrice(Client client, double price);
}
