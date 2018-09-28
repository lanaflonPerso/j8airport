package com.luxoft.examples.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class E2_Time
{
    public static void main(String[] args)
    {
        LocalTime current = LocalTime.now();

        LocalTime specific;

        specific = LocalTime.of(23, 8);
        specific = LocalTime.of(12, 10, 7);
        specific = LocalTime.of(2, 54, 19, 982);

        System.out.println(current);
    }
}
