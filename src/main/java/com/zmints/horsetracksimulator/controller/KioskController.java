package com.zmints.horsetracksimulator.controller;

import com.zmints.horsetracksimulator.model.Horse;
import com.zmints.horsetracksimulator.service.KioskInventoryService;
import com.zmints.horsetracksimulator.service.RaceHorseService;
import com.zmints.horsetracksimulator.util.DataCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

@Component
public class KioskController {
    private boolean quit = false;
    private int winningHorseNumber;

    public KioskController(){}

    @Autowired
    KioskInventoryService kioskInventoryService;

    @Autowired
    RaceHorseService raceHorseService;

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
                }
                else {
                    System.out.println("Invalid Command: " + inputCommand);
            }
        }
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
