package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTests
{

    @Autowired
    private ClientService clientService;

    @Test
    public void clientsShouldBeGeneratedTest()
    {
        List<Client> clients = clientService.findAll();

        assertTrue("--> clientsShouldBeGenerated", clients.size() > 0);
    }

}
