package domain.entities;

import java.util.Collections;

public class EliminationRace extends Race {
	
	private static final int HEATING = 100;

	private static final int ptsWINCAR = 30;
	
	public EliminationRace() {
		
	}

	public EliminationRace(String raceName, String raceType) {
		super(raceName, raceType);
	}

	@Override
	public void startRace() {

		getCarsEliminationRace();

		int competitorsRace = carsInRaceElimination.size() - 1;

		for (CarInRace carInRace : carsInRaceElimination) {
			carInRace.setDistance(0.0);
		}

		startWarmUp();

		System.out.println("                     \nCARRERA\n                    ");

		for (int i = 0; i < competitorsRace; i++) {

			raceExecution();

			Collections.sort(carsInRaceElimination, new DistanceComparator().reversed());

			System.out.println("ELIMINADO: "
					+ carsInRaceElimination.get(carsInRaceElimination.size() - 1).getCar().getBrand() + " "
					+ carsInRaceElimination.get(carsInRaceElimination.size() - 1).getCar().getModel());
			carsInRaceElimination.remove(carsInRaceElimination.size() - 1);
		}

		for (CarInRace carInRace : carsInRaceElimination) {

			carInRace.setPuntuation(carInRace.getPuntuation() + ptsWINCAR);
			System.out.println("\n\tGANADOR: " + carInRace.getCar().getBrand() + " " + carInRace.getCar().getModel()
					+ "\n\tDistancia: " + carInRace.getDistance().floatValue() + " Km" + "\n\tPuntuación: "
					+ ptsWINCAR);
		}
	}
	
	public void startWarmUp() {
		System.out.println("\n-------------------------------------------------------------");
		System.out.println("            CARRERA ELIMINACIÓN " + raceName.toUpperCase());
		System.out.println("-------------------------------------------------------------\n");
		System.out.println("                     CALENTAMIENTO                     ");

		for (int i = 0; i < HEATING; i++) {
			raceExecution();
		}

		for (CarInRace carInRace : carsInRaceElimination) {
			System.out.println(carInRace.getCar().getBrand() + " " + carInRace.getCar().getModel() + " --> "
					+ carInRace.getSpeedometer() + " km/h");
		}

	}

	public void getCarsEliminationRace() {

		for (CarInRace carInRace : carsInRace) {
			carsInRaceElimination.add(carInRace);
		}
	}




}
