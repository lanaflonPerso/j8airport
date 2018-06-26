package com.luxoft.j8airport.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Flight
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private FlightCard flightCard;

    @Column
    private ZonedDateTime departure;

    @Column
    private ZonedDateTime arrive;

    @Column
    private State state = State.NONE;

    @OneToMany
    @JoinColumn(name = "FK_FLIGHT_ID")
    private List<Ticket> ticketsBought;

    public Flight() {}

    public Flight(FlightCard flightCard)
    {
        this.flightCard = flightCard;
        this.ticketsBought = new ArrayList<>(this.flightCard.getMaxPassengers());
    }

    public void addTicket(Ticket ticket)
    {
        ticketsBought.add(ticket);
    }

    public Flight startBoarding()
    {
        state = State.BOARDING;

        while (true)
        {
            // TODO wait for at least 1 real client
            System.out.println("wait for at least 1 real client");
            return this;
        }
    }

    public void takeOff()
    {
        state = State.IN_THE_SKY;
    }

    public enum State
    {
        NONE, BOARDING, IN_THE_SKY, LANDED;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public FlightCard getFlightCard()
    {
        return flightCard;
    }

    public void setFlightCard(FlightCard flightCard)
    {
        this.flightCard = flightCard;
    }

    public ZonedDateTime getDeparture()
    {
        return departure;
    }

    public void setDeparture(ZonedDateTime departure)
    {
        this.departure = departure;
    }

    public ZonedDateTime getArrive()
    {
        return arrive;
    }

    public void setArrive(ZonedDateTime arrive)
    {
        this.arrive = arrive;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public List<Ticket> getTicketsBought()
    {
        return new ArrayList<>(ticketsBought);
    }

    public void setTicketsBought(List<Ticket> ticketsBought)
    {
        this.ticketsBought = new ArrayList<>(ticketsBought);
    }
}
