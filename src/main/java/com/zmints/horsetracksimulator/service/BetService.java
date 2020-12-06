package com.zmints.horsetracksimulator.service;

import com.zmints.horsetracksimulator.model.Bet;
import com.zmints.horsetracksimulator.model.KioskInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BetService {
    @Autowired
    KioskInventoryService kioskInventoryService;

    @Autowired
    RaceHorseService raceHorseService;

    public BetService(){
    }

    public void process(int betAmount, int horseNumber) {
        String winnerName = raceHorseService.getHorse(horseNumber).getHorseName();
        int winnings = calculateBetAmountWon(
                betAmount,
                raceHorseService.getOdds(horseNumber));
        if (kioskInventoryService.isEnoughFunds(winnings)) {
            System.out.println("Payout: " + winnerName + "," + winnings);

            List<Bet> bets = dispenseWinnings(winnings);

            System.out.println("Dispensing:");
            bets.forEach(bet -> {
                System.out.println("$" + bet.getDenomination() + "," + bet.getBillCount());
            });
        } else {
            System.out.println("Insufficient Funds: " + winnings);
        }

        kioskInventoryService.printInventory();
        raceHorseService.printHorses();

    }

    private int calculateBetAmountWon(int bet, int odds) {
        return bet * odds;
    }

    public List<Bet> dispenseWinnings(int winnings) {

        List<Bet> bets = new ArrayList<>();
        Bet bet;
        boolean betAdded = false;

        List<Integer> denominations = kioskInventoryService.getKioskInventory()
                .stream().map(KioskInventory::getDenomination).collect(Collectors.toList());

        Collections.reverse(denominations);

        for (Integer denomination : denominations) {
            int bill = denomination;
            betAdded = false;
            for (int billCount = kioskInventoryService.getInventoryByDenomination(bill).getBillCount(); billCount > 0; billCount--) {
                int totalAmountOfBills = bill * billCount;
                if (winnings >= totalAmountOfBills) {
                    bet = new Bet(bill, billCount);
                    bets.add(bet);
                    betAdded = true;
                    winnings -= totalAmountOfBills;
                    break;
                }
            }
            if (!betAdded) {
                bet = new Bet(bill, 0);
                bets.add(bet);
            }
        }

        bets.forEach(item -> {
            kioskInventoryService.decreaseInventory(item.getDenomination(), item.getBillCount());
        });

        return bets;
    }
}
