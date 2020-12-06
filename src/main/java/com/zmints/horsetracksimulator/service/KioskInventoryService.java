package com.zmints.horsetracksimulator.service;

import com.zmints.horsetracksimulator.model.KioskInventory;
import com.zmints.horsetracksimulator.repository.KioskInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KioskInventoryService {
    private static final String INVENTORY_STRING = "Inventory:";
    private static final int RESTOCK_AMOUNT = 10;
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

    public void restock(){
        List<KioskInventory> kioskInventoryList = kioskInventoryRepository.findAll();
        kioskInventoryList.stream().forEach(inventory -> {
            inventory.setBillCount(RESTOCK_AMOUNT);
            kioskInventoryRepository.save(inventory);
        });

    }


}
