package com.luxoft.j8airport.clients;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ClientService
{

    List<Client> findAll();

    Client findById(Long clientId);

    /**
     *
     * @return map where clients grouped by it's status
     */
    Map<Status, Set<Client>> getAllClientsGroupByStatus();

    /**
     *
     * @return average age of all clients in DB
     */
    int getAverageAge();

    /**
     *
     * @return average age of all clients in DB with specified @{@link Status}
     */
    int getAverageAge(Status status);

    /**
     *
     * @param flightLimit how much flights client bought
     *
     * @return all clients that have bought more then @flightLimit flights
     */
    Set<Client> getAllClientsAbove(int flightLimit);

    /**
     *
     * @return average amount of money that client spend
     */
    Double getAverageMoneySpent();

    /**
     *
     * @return all clients that spent more then average amount of money on flights
     */
    Set<Client> getAllAboveAverageSpenders();
}
