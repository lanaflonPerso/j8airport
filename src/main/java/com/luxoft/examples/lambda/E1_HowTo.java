package com.luxoft.examples.lambda;

import java.util.TreeSet;

public class E1_HowTo
{

    public static void main(String[] args)
    {
        Runnable r = () -> System.out.println("run me");

        new TreeSet<Integer>((Integer i1, Integer i2) -> 0);

        getFlyFunction().fly();
    }

    public static Flyable getFlyFunction()
    {
        return () -> System.out.println("I can fly");
    }

}

interface Flyable
{
    void fly();
}
