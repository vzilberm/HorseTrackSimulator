package com.zmints.horsetracksimulator.service;

import com.zmints.horsetracksimulator.model.KioskInventory;
import com.zmints.horsetracksimulator.repository.KioskInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KioskInventoryService {
    private static final String INVENTORY_STRING = "Inventory:";
    @Autowired
    KioskInventoryRepository kioskInventoryRepository;

    public KioskInventoryService(){

    }

   public void loadInventory(){
       kioskInventoryRepository.save(new KioskInventory(1,10));
       kioskInventoryRepository.save(new KioskInventory(5,10));
       kioskInventoryRepository.save(new KioskInventory(10,10));
       kioskInventoryRepository.save(new KioskInventory(20,10));
       kioskInventoryRepository.save(new KioskInventory(100,10));
   }



    public void printInventory() {

        Iterable<KioskInventory> inventories = kioskInventoryRepository.findAll();
        System.out.println(INVENTORY_STRING);
        inventories.forEach((inventory) -> {
            System.out.println("$"+inventory.getDenomination()
                    +","+inventory.getBillCount());
        });
    }


}
