package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.clients.ClientService;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/flights")
public class FlightsController
{

    @Autowired
    private FlightService flightService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Flight> getAllAvailableFlights(String city)
    {

        if (city != null)
        {
            return flightService.getAvailableFlightsTo(city);
        }

        return flightService.getAllAvailableFlights();
    }

    @GetMapping("buyTicket")
    public Ticket buyTicket(@RequestParam Long clientId, @RequestParam Long flightId)
    {
        return flightService.buyTicket(clientId, flightId);
    }
}
