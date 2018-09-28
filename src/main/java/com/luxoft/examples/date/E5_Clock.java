package com.luxoft.examples.date;

import java.time.Clock;
import java.time.Instant;

public class E5_Clock
{
    public static void main(String[] args)
    {
        Clock clock = Clock.systemUTC();

        clock.millis();
        clock.instant();

        Instant specific = Instant.ofEpochSecond(10_000);

        System.out.println(specific);
    }
}
