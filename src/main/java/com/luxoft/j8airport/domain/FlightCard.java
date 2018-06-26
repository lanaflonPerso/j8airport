package com.luxoft.j8airport.domain;

import javax.persistence.*;
import java.time.Duration;


@Entity
@Table
public class FlightCard
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Airport from;

    @ManyToOne
    private Airport to;

    @Column
    private int distance;

    @Column
    private Duration flightTime;

    @Column
    private int maxPassengers;

    public FlightCard()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Airport getFrom()
    {
        return from;
    }

    public void setFrom(Airport from)
    {
        this.from = from;
    }

    public Airport getTo()
    {
        return to;
    }

    public void setTo(Airport to)
    {
        this.to = to;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public String getFlightTime()
    {
        return String.valueOf(flightTime.toMinutes());
    }

    public Duration getFlightTimeObj()
    {
        return flightTime;
    }

    public void setFlightTime(Duration flightTime)
    {
        this.flightTime = flightTime;
    }

    public int getMaxPassengers()
    {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers)
    {
        this.maxPassengers = maxPassengers;
    }
}

