package com.luxoft.j8airport;

import com.luxoft.j8airport.clients.Client;
import com.luxoft.j8airport.clients.ClientSupportService;
import com.luxoft.j8airport.clients.Status;
import com.luxoft.j8airport.flights.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class J8airportApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(J8airportApplication.class, args);

        List<String> data = new ArrayList<>();


    }

    @Bean
    ClientSupportService clientSupportService()
    {
        return new ClientSupportService();
    }

    @Bean
    CommandLineRunner init(ClientSupportService clientSupportService, FlightService flightService)
    {
        return env ->
        {
            clientSupportService.generateAndStoreClients();
            flightService.loadFlights();

            setupFlight(clientSupportService, flightService);

        };
    }

    public void setupFlight(ClientSupportService clientSupportService, FlightService flightService)
    {
        System.out.println("setupFlight --> ");

        Long flightId = flightService.getAllAvailableFlights().get(1).getId();

        List<Client> platinumClients = clientSupportService.generateAndStoreClients(Status.PLATINUM, 1);
        List<Client> goldClients = clientSupportService.generateAndStoreClients(Status.GOLD, 3);
        List<Client> silverClients = clientSupportService.generateAndStoreClients(Status.SILVER, 2);
        List<Client> noneClients = clientSupportService.generateAndStoreClients(Status.NONE, 6);

        List<Client> clients = new ArrayList<>();

        clients.addAll(platinumClients);
        clients.addAll(goldClients);
        clients.addAll(silverClients);
        clients.addAll(noneClients);

        for (Client client : clients)
        {
            flightService.buyTicket(client.getId(), flightId);
        }
    }

}
