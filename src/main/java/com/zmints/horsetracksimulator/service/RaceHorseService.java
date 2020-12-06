package com.zmints.horsetracksimulator.service;

import com.zmints.horsetracksimulator.model.Horse;
import com.zmints.horsetracksimulator.model.HorseStatus;
import com.zmints.horsetracksimulator.repository.RaceHorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceHorseService {
    private static final String RACE_HORSE_STRING = "Horses:";
    @Autowired
    RaceHorseRepository raceHorseRepository;

    public RaceHorseService(){}

    public void loadHorses(){

        raceHorseRepository.save(new Horse(1, "That Darn Gray Cat", 5, HorseStatus.WON));
        raceHorseRepository.save(new Horse(2, "Fort Utopia", 10, HorseStatus.LOST));
        raceHorseRepository.save(new Horse(3, "Count Sheep", 9, HorseStatus.LOST));
        raceHorseRepository.save(new Horse(4, "Ms Traitour", 4, HorseStatus.LOST));
        raceHorseRepository.save(new Horse(5, "Real Princess", 3, HorseStatus.LOST));
        raceHorseRepository.save(new Horse(6, "Pa Kettle", 5, HorseStatus.LOST));
        raceHorseRepository.save(new Horse(7, "Gin Stinger", 6, HorseStatus.LOST));

    }

    public void printHorses() {

        Iterable<Horse> horses = raceHorseRepository.findAll();
        System.out.println(RACE_HORSE_STRING);
        horses.forEach((horse) -> {
            System.out.println(horse.getHorseNumber()
                    +","+horse.getHorseName()
                    +","+horse.getOdds()
                    +","+horse.getStatus().toString().toLowerCase());
        });

    }

    public boolean isValid(int horseNumber){
        return raceHorseRepository.findByHorseNumberEquals(horseNumber) != null;
    }

    public void setWinner(int horseNumber){
        Horse currentWinner = raceHorseRepository.findByStatusEquals(HorseStatus.WON);
        currentWinner.setStatus(HorseStatus.LOST);
        raceHorseRepository.save(currentWinner);

        Horse newWinner = raceHorseRepository.findByHorseNumberEquals(horseNumber);
        newWinner.setStatus(HorseStatus.WON);
        raceHorseRepository.save(newWinner);
    }

    public Horse getHorse(int horseNumber){
        return raceHorseRepository.findByHorseNumberEquals(horseNumber);
    }

    public boolean isWinner(int horseNumber) {
        return raceHorseRepository.findByHorseNumberEquals(horseNumber).getStatus() == HorseStatus.WON;
    }

    public int getOdds(int horseNumber) {
        Horse horse = raceHorseRepository.findByHorseNumberEquals(horseNumber);

        return horse.getOdds();
    }
}
