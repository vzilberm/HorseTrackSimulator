package com.zmints.horsetracksimulator.controller;

import com.zmints.horsetracksimulator.service.BetService;
import com.zmints.horsetracksimulator.service.KioskInventoryService;
import com.zmints.horsetracksimulator.service.RaceHorseService;
import com.zmints.horsetracksimulator.util.DataCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KioskController {
    private boolean quit = false;
    private int winningHorseNumber;

    public KioskController(){}

    @Autowired
    KioskInventoryService kioskInventoryService;

    @Autowired
    RaceHorseService raceHorseService;

    @Autowired
    BetService betService;

    public void loadData(){
        kioskInventoryService.loadInventory();
        raceHorseService.loadHorses();
    }

    public void initialPrint() {

        kioskInventoryService.printInventory();
        raceHorseService.printHorses();
    }

    public void executeCommand(String inputCommand){
        System.out.println("Command String is: " + inputCommand);
        if (inputCommand.length() > 0){
                String[] commandElements = inputCommand.split(" ");
                if (commandElements[0].equalsIgnoreCase("q")){
                    quit = true;
                }
                else if(commandElements[0].equalsIgnoreCase("r")){
                    restockInventory();
                }
                else if(commandElements[0].equalsIgnoreCase("w") &&
                        DataCheck.isNumeric(commandElements[1])){
                    winningHorseNumber = Integer.parseInt(commandElements[1]);
                    declareWinner(winningHorseNumber);
                } else if (DataCheck.isNumeric(commandElements[0] ) && DataCheck.isNumeric(commandElements[1])){
                    processBet(Integer.parseInt(commandElements[0]), Integer.parseInt(commandElements[1]));
                }
            else {
                    System.out.println("Invalid Command: " + inputCommand);
                }
        }
    }

    private void processBet(int horseNumber, int betAmount) {
        if (!raceHorseService.isValid(horseNumber)){
            System.out.println("Invalid Horse Number: " + horseNumber);
            return;
        }

        if (!raceHorseService.isWinner(horseNumber)){
            System.out.println("No Payout: "+ raceHorseService.getHorse(horseNumber).getHorseName());
            return;
        }

        betService.process(betAmount, horseNumber);
    }

    private void declareWinner(int winningHorseNumber) {
        if (raceHorseService.isValid(winningHorseNumber)) {
            raceHorseService.setWinner(winningHorseNumber);
            raceHorseService.printHorses();
        }else{
            System.out.println("Invalid Horse Number: " + winningHorseNumber);
        }
    }


    public boolean quit(){
        return quit;
    }

    public void restockInventory(){
        kioskInventoryService.restock();
        kioskInventoryService.printInventory();

    }


}
