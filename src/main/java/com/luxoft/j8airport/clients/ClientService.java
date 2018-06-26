package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;

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

}
