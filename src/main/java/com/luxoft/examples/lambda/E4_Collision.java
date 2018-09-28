package com.luxoft.examples.lambda;

import java.util.Arrays;
import java.util.List;

public class E4_Collision { }

interface A extends X, Z
{
    @Override
    default void test()
    {
        X.super.test();
        Z.super.test();
    }
}

interface Z
{
    default void test()
    {
        System.out.println("Zzz...");
    }
}

interface X
{
    default void test()
    {
        System.out.println("Xxx...");
    }
}