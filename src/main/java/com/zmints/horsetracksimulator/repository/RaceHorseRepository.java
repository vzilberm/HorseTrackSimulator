package com.zmints.horsetracksimulator.repository;

import com.zmints.horsetracksimulator.model.Horse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RaceHorseRepository extends CrudRepository<Horse, Integer> {
    List<Horse> findAll();
    Horse findByHorseNumber(int horseNumber);
}
