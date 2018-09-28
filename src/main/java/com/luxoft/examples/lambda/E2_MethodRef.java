package com.luxoft.examples.lambda;

import java.util.Arrays;

public class E2_MethodRef
{

    public static void main(String[] args)
    {
        E2_MethodRef demo = new E2_MethodRef();

        String[] names = {"Oleg", "Anna", "Igor", "irina", "Ivan"};

        System.out.println(Arrays
                .toString(demo.convert(names, E2_MethodRef::nameToInt)));
    }

    public int[] convert(String[] data, Converter converter)
    {
        int[] result = new int[data.length];

        for (int i = 0; i < data.length; i++)
        {
            result[i] = converter.convert(data[i]);
        }

        return result;
    }


    public static int nameToInt(String name)
    {
        return name == null ? 0 : name.length();
    }
}

interface Converter
{
    int convert(String s);
}
