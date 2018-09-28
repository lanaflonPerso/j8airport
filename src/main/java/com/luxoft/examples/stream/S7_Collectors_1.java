package com.luxoft.examples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class S7_Collectors_1
{
    public static void main(String[] args)
    {
        List<Person> people = getPersonList();

        String result = people.stream()
                .filter(p -> p.getAge() >= 21)
                .map(p -> p.getName())
                .collect(Collectors.joining(", "));

        System.out.println(result);
    }

    public static List<Person> getPersonList()
    {
        int[] ages = {23, 18, 37, 87, 46};
        String[] names = {"Oleg", "Anna", "Irina", "Ivan", "Alexey"};

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < names.length; i++)
        {
            Person p = new Person();

            p.setName(names[i]);
            p.setAge(ages[i]);

            people.add(p);
        }

        return people;
    }
}
