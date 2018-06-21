package com.luxoft.j8airport.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientSupportService
{
    @Autowired
    private ClientRepository repository;

    public void generateAndStoreClients()
    {
        repository.save(generateClient("Oleg", 34, Client.Gender.MALE));
    }

    private Client generateClient(String name, int age, Client.Gender gender)
    {
        Client c = new Client();

        c.setName(name);
        c.setAge(age);
        c.setGender(gender);

        return c;
    }
}
