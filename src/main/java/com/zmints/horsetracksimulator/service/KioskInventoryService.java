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

    public List<KioskInventory> getKioskInventory(){
        return kioskInventoryRepository.findAll();
    }

    public KioskInventory  getInventoryByDenomination(int denomination) {
        return kioskInventoryRepository.findByDenominationEquals(denomination);
    }

    public void decreaseInventory(int denomination, int amount) {

        KioskInventory inventory = kioskInventoryRepository.findByDenominationEquals(denomination);

        int billCount = inventory.getBillCount();
        if ((billCount - amount) >= 0) {
            inventory.setBillCount(billCount - amount);
            kioskInventoryRepository.save(inventory);
        }
    }

    public boolean isEnoughFunds(int amountWon) {

        List<KioskInventory> inventories = kioskInventoryRepository.findAll();
        Integer funds = inventories.stream().reduce(0,
                (total, inventory) -> total + (inventory.getDenomination() * inventory.getBillCount()), Integer::sum);
        return (funds - amountWon) >= 0;
    }

}
