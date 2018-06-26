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

    /**
     *
     * @return return all flights that waiting for passengers
     */
    List<Flight> getAllAvailableFlights();

    /**
     *
     * @return returns all active flights to specific city
     */
    List<Flight> getAvailableFlightsTo(String city);

    /**
     *
     * @return returns all active flights from specific city
     */
    List<Flight> getAvailableFlightsFrom(String city);

    /**
     *
     * @return returns all active flights with specific departure and destination
     */
    List<Flight> getAvailableFlights(String from, String to);

    /**
     *
     * @return returns all passengers grouped by status for specific flight
     */
    Map<Status, Set<Client>> getPassengersGroupedByStatus(Flight flight);

    /**
     *
     * @return returns all passengers with specific status for specific flight
     */
    List<Client> getPassengersWith(Flight flight, Status status);

    void loadFlights();

    /**
     *
     * @return returns all passengers with specific status for specific flight
     */
    Ticket buyTicket(Long clientId, Long flightId);
}
