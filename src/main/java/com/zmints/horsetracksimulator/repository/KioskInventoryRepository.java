package com.zmints.horsetracksimulator.repository;

import com.zmints.horsetracksimulator.model.Horse;
import com.zmints.horsetracksimulator.model.KioskInventory;
import org.springframework.data.repository.CrudRepository;

public interface KioskInventoryRepository extends CrudRepository<KioskInventory, Integer> {
}
