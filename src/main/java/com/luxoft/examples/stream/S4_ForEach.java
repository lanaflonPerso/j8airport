package com.luxoft.examples.stream;

import java.util.stream.Stream;

public class S4_ForEach
{
    public static void main(String[] args)
    {
        Stream.of(26, 78, 34, 16, 18)
                .forEach(n -> System.out.println(n));
    }

}
