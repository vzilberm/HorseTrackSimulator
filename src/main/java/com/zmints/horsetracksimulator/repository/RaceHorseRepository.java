package com.zmints.horsetracksimulator.repository;

import com.zmints.horsetracksimulator.model.Horse;
import com.zmints.horsetracksimulator.model.HorseStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RaceHorseRepository extends CrudRepository<Horse, Integer> {
    List<Horse> findAll();
    Horse findByHorseNumberEquals(int horseNumber);
    Horse findByStatusEquals(HorseStatus status);
}
