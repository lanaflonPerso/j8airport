package com.luxoft.examples.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class E9_TemporalAdjusters
{
    public static void main(String[] args)
    {
        LocalDate firstDayOfMonth = LocalDate.now()
                .with(TemporalAdjusters.firstDayOfMonth());

        System.out.println(firstDayOfMonth);


        LocalDate lastDayOfYear = LocalDate.now()
                .with(TemporalAdjusters.lastDayOfYear());

        System.out.println(lastDayOfYear);

        LocalDate lastSundayThisMonth = LocalDate.now()
                .with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));

        System.out.println(lastSundayThisMonth);
    }
}
