package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTests
{

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientSupportService clientSupportService;

    private static final int PLATINUM_COUNT = 1;
    private static final int GOLD_COUNT = 3;
    private static final int SILVER_COUNT = 2;
    private static final int NONE_COUNT = 6;

    @Before
    public void setup()
    {
        clientSupportService.deleteAll();

        clientSupportService.generateAndStoreClients(Status.PLATINUM, PLATINUM_COUNT);
        clientSupportService.generateAndStoreClients(Status.GOLD, GOLD_COUNT);
        clientSupportService.generateAndStoreClients(Status.SILVER, SILVER_COUNT);
        clientSupportService.generateAndStoreClients(Status.NONE, NONE_COUNT);
    }

    @After
    public void cleanDB()
    {
        clientSupportService.deleteAll();
    }

    @Test
    public void clientsShouldBeGeneratedTest()
    {
        List<Client> clients = clientService.findAll();

        assertTrue("--> clientsShouldBeGenerated", clients.size() > 0);
    }

    @Test
    public void findByIdTest()
    {
        Client expected = clientSupportService.generateAndStoreClient("Ivan", 34, Client.Gender.MALE);

        Client actual = clientService.findById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest()
    {
        clientSupportService.deleteAll();

        int count = 10;

        clientSupportService.generateAndStoreClients(Status.PLATINUM, count);

        List<Client> generatedClients = clientService.findAll()
                .stream()
                .filter(c -> c.getName().startsWith(ClientSupportService.DEFAULT_CLIENT_NAME_PREFIX))
                .collect(Collectors.toList());

        assertEquals(count, generatedClients.size());
    }

    @Test
    public void getAllClientsGroupByStatusTest1()
    {
        Map<Status, Set<Client>> clients = clientService.getAllClientsGroupByStatus();

        assertEquals(PLATINUM_COUNT, clients.get(Status.PLATINUM).size());
    }

    @Test
    public void getAllClientsGroupByStatusTest2()
    {
        Map<Status, Set<Client>> clients = clientService.getAllClientsGroupByStatus();

        assertEquals(GOLD_COUNT, clients.get(Status.GOLD).size());
    }

    @Test
    public void getAllClientsGroupByStatusTest3()
    {
        Map<Status, Set<Client>> clients = clientService.getAllClientsGroupByStatus();

        assertEquals(SILVER_COUNT, clients.get(Status.SILVER).size());
    }

    @Test
    public void getAllClientsGroupByStatusTest4()
    {
        Map<Status, Set<Client>> clients = clientService.getAllClientsGroupByStatus();

        assertEquals(NONE_COUNT, clients.get(Status.NONE).size());
    }

    @Test
    public void getAverageAgeTest1()
    {
        double actual = clientService.getAverageAge();

        assertEquals(ClientSupportService.DEFAULT_AGE, actual, 0);
    }

    @Test
    public void getAverageAgeTest2()
    {
        int plusAge = 46;
        clientSupportService.generateAndStoreClient("Ivan", plusAge, Client.Gender.MALE);

        int countOfGeneratedClients = PLATINUM_COUNT + GOLD_COUNT + SILVER_COUNT + NONE_COUNT;

        double expectedAge = (ClientSupportService.DEFAULT_AGE * countOfGeneratedClients + plusAge)
                / (countOfGeneratedClients + 1.0);

        double actual = clientService.getAverageAge();

        assertEquals(expectedAge, actual, 0);
    }
}
