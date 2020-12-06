package com.zmints.horsetracksimulator.model;

public class Bet {
    private final int denomination;
    private final int billCount;

    public Bet(int denomination, int billCount) {
        this.denomination = denomination;
        this.billCount = billCount;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getBillCount() {
        return billCount;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("Bet{");
        stringBuilder.append("denomination=").append(denomination);
        stringBuilder.append(", billCount=").append(billCount);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
