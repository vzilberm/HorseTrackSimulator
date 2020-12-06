package com.zmints.horsetracksimulator.controller;

import com.zmints.horsetracksimulator.model.Horse;
import com.zmints.horsetracksimulator.model.HorseStatus;
import com.zmints.horsetracksimulator.service.BetService;
import com.zmints.horsetracksimulator.service.KioskInventoryService;
import com.zmints.horsetracksimulator.service.RaceHorseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class KioskControllerTest{

    @InjectMocks
    KioskController kioskController;

    @Mock
    KioskInventoryService kioskInventoryService;

    @Mock
    RaceHorseService raceHorseService;

    @Mock
    BetService betService;

    @Before
    public void setUp()    {

    }

    @Test
    public void quit() {
        kioskController.executeCommand("q");

        Assert.assertTrue(kioskController.quit());
    }

    @Test
    public void restockInventory()  {
        kioskController.executeCommand("r");

        verify(kioskInventoryService).restock();
        verify(kioskInventoryService).printInventory();
    }

    @Test
    public void process_Bet_invalid_horse()   {
        when(raceHorseService.isValid(10)).thenReturn(false);

        kioskController.executeCommand("10 100");

        verify(betService, never()).process(100, 10);
    }

    @Test
    public void process_Bet_no_payout()   {
        Horse horse = new Horse(10, "This horse wins", 8, HorseStatus.LOST);

        when(raceHorseService.isValid(10)).thenReturn(true);
        when(raceHorseService.isWinner(10)).thenReturn(false);
        when(raceHorseService.getHorse(10)).thenReturn(horse);

        kioskController.executeCommand("10 100");

        verify(betService, never()).process(100, 10);
    }

    @Test
    public void process_bet_payout()    {
        when(raceHorseService.isValid(10)).thenReturn(true);
        when(raceHorseService.isWinner(10)).thenReturn(true);

        kioskController.executeCommand("10 100");

        verify(betService).process(100, 10);
    }

    @Test
    public void declare_winner_invalid_horse()    {
        when(raceHorseService.isValid(10)).thenReturn(false);

        kioskController.executeCommand("w 10");

        verify(raceHorseService, never()).setWinner(10);
        verify(raceHorseService, never()).printHorses();
    }

    @Test
    public void declare_winner_valid_horse()    {
        when(raceHorseService.isValid(10)).thenReturn(true);

        kioskController.executeCommand("w 10");

        verify(raceHorseService).setWinner(10);
        verify(raceHorseService).printHorses();
    }

}
