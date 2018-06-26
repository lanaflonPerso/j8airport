package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/clients")
public class ClientController
{
    @Autowired
    private ClientService clientService;

    @GetMapping
    List<Client> findAll()
    {
        return clientService.findAll();
    }
}
