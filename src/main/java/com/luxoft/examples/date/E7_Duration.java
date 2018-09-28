package com.luxoft.examples.date;

import java.time.*;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class E7_Duration
{
    public static void main(String[] args)
    {
        LocalDateTime from = LocalDateTime.of(2010, 01, 01, 0, 0);
        LocalDateTime to = LocalDateTime.now();

        Duration duration = Duration.between(from, to);

        System.out.println(duration.toDays());
    }
}
