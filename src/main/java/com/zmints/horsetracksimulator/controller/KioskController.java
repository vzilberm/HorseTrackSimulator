package com.zmints.horsetracksimulator.controller;

import com.zmints.horsetracksimulator.model.Horse;
import com.zmints.horsetracksimulator.service.KioskInventoryService;
import com.zmints.horsetracksimulator.service.RaceHorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KioskController {

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



}
