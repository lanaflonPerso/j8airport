package com.luxoft.j8airport.domain;

import javax.persistence.*;

@Entity
@Table
public class Ticket
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private Client client;

    @Column
    private double price;

    private double discount;

    public Ticket()
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

    public Flight getFlight()
    {
        return flight;
    }

    public void setFlight(Flight flight)
    {
        this.flight = flight;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
}
