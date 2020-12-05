package com.zmints.horsetracksimulator;

import com.zmints.horsetracksimulator.controller.KioskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HorsetracksimulatorApplication implements CommandLineRunner {
	@Autowired
	KioskController kioskController;

	public static void main(String[] args) {
		SpringApplication.run(HorsetracksimulatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		kioskController.loadData();
		kioskController.initialPrint();

	}
}
