package com.luxoft.examples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class S1_Persons
{
    public static void main(String[] args)
    {
        List<Person> people = getPersonList();

        OptionalDouble averageAge = people.stream()
                .mapToInt(p -> p.getAge())
                .filter(age -> age >= 21)
                .average();

        System.out.println(averageAge.getAsDouble());
    }

    public static List<Person> getPersonList()
    {
        int[] data = {26, 78, 34, 16, 18, 9, 23};

        List<Person> people = new ArrayList<>();

        for (Integer age : data)
        {
            people.add(new Person(age));
        }

        return people;
    }
}
