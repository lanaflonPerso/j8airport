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
        };
    }

}
