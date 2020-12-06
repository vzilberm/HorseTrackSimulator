package com.zmints.horsetracksimulator.repository;

import com.zmints.horsetracksimulator.model.KioskInventory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KioskInventoryRepository extends CrudRepository<KioskInventory, Integer> {
    List<KioskInventory> findAll();
    KioskInventory findByDenominationEquals(int denomination);
}
