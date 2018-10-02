package com.luxoft.j8airport.flights;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

public class TimezoneUtils
{
    /**
     * Returns all time zones for Europe
     */
    public static List<String> getAllEuropeTimeZones()
    {
        return null;
    }

    /**
     * Returns all time zones according to the provided filter.
     *
     * Example:
     *
     * Filter - Asia, returns all time zones from Asia
     *
     *
     */
    public static List<String> getTimeZonesStartedWith(String filter)
    {
        return null;
    }


    /**
     *
     * Adds duration to the source date and covert it to the timezone with provided zoneId.
     *
     * @param date source date
     * @param durationInMinutes duration to add in minutes
     * @param zoneId zone id of target date time
     * @return
     */
    public static ZonedDateTime datePlusDuration(ZonedDateTime date, Duration durationInMinutes, String zoneId)
    {
        return ZonedDateTime.ofInstant(
                date.plusMinutes(durationInMinutes.toMinutes()).toInstant(),
                ZoneId.of(zoneId));
    }



    public static void main(String[] args)
    {
        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(s -> s.startsWith("Europe"))

                .collect(Collectors
                        .toMap(s -> s,
                                s -> ZonedDateTime.now(ZoneId.of(s))
                                        .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))))
                .entrySet()
                .stream()
                .map(e -> e.getKey() + " : " + e.getValue())
                .sorted()
                .forEach(System.out::println);
    }
}
