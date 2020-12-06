package com.zmints.horsetracksimulator.model;

import javax.persistence.*;

@Entity
@Table(name = "Horse")
public class Horse {

    public Horse() {
    }

    public Horse(int horseNumber, String horseName, int odds, HorseStatus status) {
        this.horseNumber = horseNumber;
        this.horseName = horseName;
        this.odds = odds;
        this.status = status;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int horseNumber;

    @Column
    private String horseName;

    @Column
    private int odds;

    @Column
    private HorseStatus status = HorseStatus.LOST;

    public int getHorseNumber() {
        return horseNumber;
    }

    public String getHorseName() {
        return horseName;
    }

    public int getOdds() {
        return odds;
    }

    public HorseStatus getStatus() {
        return status;
    }

    public void setStatus(HorseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("Horse{");
        stringBuilder.append("id=").append(id);
        stringBuilder.append(", horseNumber=").append(horseNumber);
        stringBuilder.append(", horseName='").append(horseName).append('\'');
        stringBuilder.append(", odds=").append(odds);
        stringBuilder.append(", horseStatus=").append(status);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}