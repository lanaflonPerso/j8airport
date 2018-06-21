package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.flights.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/flights")
public class FlightsController
{

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllAvailableFlights(String city)
    {

        if (city != null)
        {
            return flightService.getAvailableFlightsTo(city);
        }

        return flightService.getAllAvailableFlights();
    }
}
