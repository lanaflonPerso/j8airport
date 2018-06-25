package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.clients.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


/**
 * TODO: add mock @{@link com.luxoft.j8airport.tickets.TicketRepository} to get fake tickets data
 * for every @{@link Status}
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalDiscountServiceTests
{

    @Autowired
    private ClientSupportService clientSupportService;

    @Autowired
    private DiscountService discountService;

    @Test
    public void getPersonalPriceTest1()
    {
        Client client = clientSupportService.generateAndStoreClients(Status.NONE, 1).get(0);

        double beforeDiscount = 10;

        double expected = 10;
        double actual = discountService.getPersonalPrice(client, beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.NONE ", expected, actual, 0);
    }

    @Test
    public void getPersonalPriceTest2()
    {
        Client client = clientSupportService.generateAndStoreClients(Status.SILVER, 1).get(0);

        double beforeDiscount = 10;

        double expected = 9.5;
        double actual = discountService.getPersonalPrice(client, beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.SILVER ", expected, actual, 0);
    }

    @Test
    public void getPersonalPriceTest3()
    {
        Client client = clientSupportService.generateAndStoreClients(Status.GOLD, 1).get(0);

        double beforeDiscount = 10;

        double expected = 9;
        double actual = discountService.getPersonalPrice(client, beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.GOLD ", expected, actual, 0);
    }

    @Test
    public void getPersonalPriceTest4()
    {
        Client client = clientSupportService.generateAndStoreClients(Status.PLATINUM, 1).get(0);

        double beforeDiscount = 10;

        double expected = 8.5;
        double actual = discountService.getPersonalPrice(client, beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.PLATINUM ", expected, actual, 0);
    }

}
