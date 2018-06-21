package com.luxoft.j8airport.clients;

import com.luxoft.j8airport.tickets.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Map<Status, Set<Client>> getAllClientsGroupByStatus()
    {
        return clientRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getStatus(), Collectors.toSet()));
    }

    @Override
    public int getAverageAge()
    {
        return getAverageAge(clientRepository.findAll());
    }

    @Override
    public int getAverageAge(Status status)
    {
        return getAverageAge(clientRepository.findByStatus(status));
    }

    private int getAverageAge(List<Client> clients)
    {
        return (int) clients.stream()
                .mapToInt(c -> c.getAge())
                .average().getAsDouble();
    }

    @Override
    public Set<Client> getAllClientsAbove(int flightLimit)
    {
        return getClientToTickets().entrySet()
                .stream()
                .filter(e -> e.getValue() > flightLimit)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    private Map<Client, Long> getClientToTickets()
    {
        return ticketRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getClient(), Collectors.counting()));
    }

    @Override
    public Double getAverageMoneySpent()
    {

        return getClientToSpending().entrySet()
                .stream()
                .mapToDouble(e -> e.getValue())
                .average().getAsDouble();
    }

    @Override
    public Set<Client> getAllAboveAverageSpenders()
    {
        double averageMoneySpent = getAverageMoneySpent();

        return getClientToSpending()
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > averageMoneySpent)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    private Map<Client, Double> getClientToSpending()
    {
        return ticketRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getClient(),
                        Collectors.averagingDouble(t -> t.getPrice())));
    }

}
