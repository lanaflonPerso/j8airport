package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;
import com.luxoft.j8airport.domain.Flight;
import com.luxoft.j8airport.domain.FlightCard;
import com.luxoft.j8airport.domain.Ticket;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FlightService
{

    Flight findById(Long flightId);

    void setUpFlight(FlightCard template);

    List<Flight> getAllAvailableFlights();

    List<Flight> getAvailableFlightsTo(String city);

    List<Flight> getAvailableFlightsFrom(String city);

    List<Flight> getAvailableFlights(String from, String to);

    Map<Status, Set<Client>> getPassengersGroupedByStatus(Flight flight);

    List<Client> getPassengersWith(Flight flight, Status status);

    void loadFlights();

    Ticket buyTicket(Long clientId, Long flightId);
}
