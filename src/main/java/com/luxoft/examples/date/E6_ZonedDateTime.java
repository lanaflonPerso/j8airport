package com.luxoft.examples.date;

import java.time.*;

public class E6_ZonedDateTime
{
    public static void main(String[] args)
    {
        ZonedDateTime current = ZonedDateTime.now();

        ZonedDateTime specific = ZonedDateTime.now(ZoneId.of("Europe/Kiev"));

        System.out.println(current);
        System.out.println(specific);

    }
}
