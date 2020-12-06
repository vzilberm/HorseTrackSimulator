package com.zmints.horsetracksimulator.service;

import com.zmints.horsetracksimulator.model.KioskInventory;
import com.zmints.horsetracksimulator.repository.KioskInventoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class KioskInventoryServiceTest {
    @InjectMocks
    KioskInventoryService kioskInventoryService;
    @Mock
    KioskInventoryRepository kioskInventoryRepository;

    @Before
    public void setup(){

    }

    @Test
    public void isEnoughFunds_happy(){
        List<KioskInventory> currentInventory = new ArrayList<KioskInventory>();
        currentInventory.add(new KioskInventory(1, 10));
        currentInventory.add(new KioskInventory(5, 10));
        currentInventory.add(new KioskInventory(10, 10));
        currentInventory.add(new KioskInventory(20, 10));
        currentInventory.add(new KioskInventory(100, 10));
        when(kioskInventoryRepository.findAll()).thenReturn(currentInventory);
        assertTrue(kioskInventoryService.isEnoughFunds(500));
    }

    @Test
    public void isEnoughFunds_not_enough(){
        List<KioskInventory> currentInventory = new ArrayList<KioskInventory>();
        currentInventory.add(new KioskInventory(1, 1));
        currentInventory.add(new KioskInventory(5, 1));
        currentInventory.add(new KioskInventory(10, 1));
        currentInventory.add(new KioskInventory(20, 1));
        currentInventory.add(new KioskInventory(100, 1));
        when(kioskInventoryRepository.findAll()).thenReturn(currentInventory);
        assertFalse(kioskInventoryService.isEnoughFunds(500));
    }

}