package com.luxoft.examples.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class E8_Period
{
    public static void main(String[] args)
    {
        LocalDate from = LocalDate.of(2010, 01, 01);

        Period period = Period.between(from, LocalDate.now());

        System.out.println(period.getMonths());



    }
}
