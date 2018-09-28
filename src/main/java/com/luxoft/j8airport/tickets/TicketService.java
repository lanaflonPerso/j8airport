package com.luxoft.j8airport.tickets;

import com.luxoft.j8airport.domain.Client;
import com.luxoft.j8airport.domain.Ticket;
import com.luxoft.j8airport.domain.Flight;

import java.util.Set;

public interface TicketService
{

    Ticket calculateAndApplyDiscount(Ticket ticket);

    /**
     *
     * Creates ticket with personal price.
     *
     * @param client
     * @param flight
     *
     * @return
     */
    Ticket createTicket(Client client, Flight flight);

    /**
     *
     * @return average amount of money that client spend on one ticket
     */
    Double getAverageMoneySpent();

    /**
     *
     * @return all clients that spent more then average amount of money on flights
     */
    Set<Client> getAllAboveAverageSpenders();

    /**
     *
     * @param flightLimit how much flights client bought
     *
     * @return all clients that have bought more then @flightLimit flights
     */
    Set<Client> getAllClientsAbove(int flightLimit);

    void setRepository(TicketRepository repository);

}
