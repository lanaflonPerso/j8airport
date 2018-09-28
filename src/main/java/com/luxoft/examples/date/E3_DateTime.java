package com.luxoft.examples.date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class E3_DateTime
{
    public static void main(String[] args)
    {
        LocalDateTime current = LocalDateTime.now();

        LocalDateTime specific = LocalDateTime.of(2018, Month.AUGUST, 23, 23, 56);

        System.out.println(specific);
    }
}
