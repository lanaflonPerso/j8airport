package com.luxoft.examples.lambda;

import java.util.Arrays;
import java.util.List;

public class E3_ForEach
{
    public static void main(String[] args)
    {
        List<String> names = Arrays.asList(new String[] {"Oleg", "Anna", "Igor", "irina", "Ivan"});

        names.forEach((name) -> System.out.println(name));
    }
}
