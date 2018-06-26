package com.luxoft.j8airport.tickets.discounts;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.domain.Status;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.tickets.TicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalDiscountServiceTests
{

    @Autowired
    private ClientSupportService clientSupportService;

    @Autowired
    private DiscountService discountService;

    @Mock
    private TicketRepository ticketRepositoryMock;

    private Map<Status, Client> clientMap;

    @Before
    public void setup()
    {
        clientMap = new HashMap<>(4);

        clientMap.put(Status.NONE, clientSupportService.generateAndStoreClients(Status.NONE, 1).get(0));
        clientMap.put(Status.SILVER, clientSupportService.generateAndStoreClients(Status.SILVER, 1).get(0));
        clientMap.put(Status.GOLD, clientSupportService.generateAndStoreClients(Status.GOLD, 1).get(0));
        clientMap.put(Status.PLATINUM, clientSupportService.generateAndStoreClients(Status.PLATINUM, 1).get(0));

        ticketRepositoryMock = mock(TicketRepository.class);

        List<Ticket> noPrice = Arrays.asList(new Ticket[] {new Ticket()});

        Ticket silverPlus = mock(Ticket.class);
        when(silverPlus.getPrice()).thenReturn(Status.SILVER.getMoneySpentLimit() + 1.0);

        Ticket goldPlus = mock(Ticket.class);
        when(goldPlus.getPrice()).thenReturn(Status.GOLD.getMoneySpentLimit() + 1.0);

        Ticket platinumPlus = mock(Ticket.class);
        when(platinumPlus.getPrice()).thenReturn(Status.PLATINUM.getMoneySpentLimit() + 1.0);



        when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.NONE))).thenReturn(noPrice);

        when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.SILVER)))
                .thenReturn(Arrays.asList(new Ticket[] { silverPlus }));

        when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.GOLD)))
                .thenReturn(Arrays.asList(new Ticket[] { goldPlus }));

        when(ticketRepositoryMock.findAllByClient(clientMap.get(Status.PLATINUM)))
                .thenReturn(Arrays.asList(new Ticket[] { platinumPlus }));

        discountService.setTicketRepository(ticketRepositoryMock);
    }

    public void clear()
    {
        clientSupportService.deleteAllGeneratedClients();
    }

    @Test
    public void getPersonalPriceTest1()
    {
        double beforeDiscount = 10;

        double expected = 10;
        double actual = discountService.getPersonalPrice(clientMap.get(Status.NONE), beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.NONE ", expected, actual, 0);
    }

    @Test
    public void getPersonalPriceTest2()
    {
        double beforeDiscount = 10;

        double expected = 9.5;
        double actual = discountService.getPersonalPrice(clientMap.get(Status.SILVER), beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.SILVER ", expected, actual, 0);
    }

    @Test
    public void getPersonalPriceTest3()
    {
        double beforeDiscount = 10;

        double expected = 9;
        double actual = discountService.getPersonalPrice(clientMap.get(Status.GOLD), beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.GOLD ", expected, actual, 0);
    }

    @Test
    public void getPersonalPriceTest4()
    {
        double beforeDiscount = 10;

        double expected = 8.5;
        double actual = discountService.getPersonalPrice(clientMap.get(Status.PLATINUM), beforeDiscount);

        assertEquals("--> getPersonalPrice for client with Status.PLATINUM ", expected, actual, 0);
    }

}
