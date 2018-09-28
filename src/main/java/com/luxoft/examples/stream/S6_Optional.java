package com.luxoft.examples.stream;

import java.util.OptionalInt;
import java.util.stream.Stream;

public class S6_Optional
{
    public static void main(String[] args)
    {
        OptionalInt result = Stream.of(26, 78, 34, 16, 18)
                .mapToInt(n -> n.intValue())
                .filter(n -> n < 0)
                .min();

        System.out.println(result);
    }

}
