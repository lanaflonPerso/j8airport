package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Status;

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
    double getAverageAge();

    /**
     *
     * @return average age of all clients in DB with specified @{@link Status}
     */
    double getAverageAge(Status status);

}
