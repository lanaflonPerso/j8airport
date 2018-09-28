package com.luxoft.examples.stream;

import java.util.stream.Stream;

public class S3_ReduceParallel
{
    public static void main(String[] args)
    {
        int sum = Stream
                    .of(26, 78, 34, 16, 18)
                    .parallel()
                    .reduce(0, (a, b) -> a + b);

        System.out.println(sum);
    }

}
