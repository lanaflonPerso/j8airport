package com.luxoft.examples.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;

public class E4_Instant
{
    public static void main(String[] args)
    {
        Instant current = Instant.now();

        Instant specific = Instant.ofEpochSecond(10_000);

        System.out.println(specific);
    }
}
