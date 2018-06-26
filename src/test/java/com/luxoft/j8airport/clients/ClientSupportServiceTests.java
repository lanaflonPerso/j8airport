package com.luxoft.j8airport.clients;

import static  org.junit.Assert.*;

import com.luxoft.j8airport.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientSupportServiceTests
{

    @Autowired
    private ClientSupportService clientSupportService;

    @Autowired
    private ClientService clientService;

    @Test
    public void generateAndStoreClientTest()
    {
        String name = "Irina";
        int age = 18;
        Client.Gender gender = Client.Gender.FEMALE;

        Client expected = clientSupportService.generateAndStoreClient(name, age, gender);

        Client actual = clientService.findById(expected.getId());

        assertEquals("generated client should be stored to DB", expected, actual);
    }

}
