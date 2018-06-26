package com.luxoft.j8airport.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Client
{

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private Gender gender;

    @Column
    private Status status;

    public enum Gender
    {
        MALE, FEMALE;
    }

    public Client()
    {
        status = Status.NONE;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof Client))
        {
            return false;
        }

        Client client = (Client) o;
        return id == client.id &&
                age == client.age &&
                Objects.equals(name, client.name) &&
                gender == client.gender;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, age, gender);
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", status=" + status +
                '}';
    }
}
