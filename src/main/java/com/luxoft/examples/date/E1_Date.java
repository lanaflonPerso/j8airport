package com.luxoft.examples.date;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class E1_Date
{
    public static void main(String[] args)
    {
        LocalDate current = LocalDate.now();

        LocalDate specific = LocalDate.of(2018, 8, 9);
        specific = LocalDate.of(2018, Month.AUGUST, 9);


        specific.plus(23, ChronoUnit.DAYS);
    }
}
