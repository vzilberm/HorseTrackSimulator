package com.zmints.horsetracksimulator.model;


import javax.persistence.*;

@Entity
@Table(name = "KioskInventory")
public class KioskInventory {

    public KioskInventory() {
    }

    public KioskInventory(int denomination, int billCount) {
        this.denomination = denomination;
        this.billCount = billCount;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int denomination;

    @Column
    private int billCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getBillCount() {
        return billCount;
    }

    public void setBillCount(int billCount) {
        this.billCount = billCount;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("Inventory{");
        stringBuilder.append("id=").append(id);
        stringBuilder.append(", denomination=").append(denomination);
        stringBuilder.append(", billCount=").append(billCount);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}





