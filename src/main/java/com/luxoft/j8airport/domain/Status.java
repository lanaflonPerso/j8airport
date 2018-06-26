package com.luxoft.j8airport.domain;

public enum Status
{
    PLATINUM(100000), GOLD(10000), SILVER(5000), NONE(0);

    int moneySpentLimit;

    Status(int moneySpentLimit)
    {
        this.moneySpentLimit = moneySpentLimit;
    }

    public int getMoneySpentLimit()
    {
        return moneySpentLimit;
    }
}
