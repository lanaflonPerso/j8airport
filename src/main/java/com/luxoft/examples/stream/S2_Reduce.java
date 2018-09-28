package com.luxoft.examples.stream;

import java.util.stream.Stream;

public class S2_Reduce
{
    public static void main(String[] args)
    {
        int sum = Stream
                    .of(26, 78, 34, 16, 18)
                    .reduce(0, (a, b) -> a + b);

        System.out.println(sum);
    }

}
