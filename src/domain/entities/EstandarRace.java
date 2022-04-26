package domain.entities;

import java.util.Collections;

public class EstandarRace extends Race {

	private float raceDuration;

	public EstandarRace() {

	}

	public EstandarRace(String raceName, String raceType, float raceDuration) {
		super(raceName, raceType);
		this.raceDuration = raceDuration;
	}

	@Override
	public void startRace() {

		for (CarInRace carInRace : carsInRace) {
			carInRace.setDistance(0.0);
		}

		for (int i = 0; i < raceDuration * 60; i++) {
			raceExecution();
		}
		getRacePuntuation();
	}

	public void getRacePuntuation() {

		int puntuationAccumulator = 40;
		Collections.sort(carsInRace, new DistanceComparator().reversed());
		byte podium = 1;
		System.out.println("\n-------------------------------------------------------------");
		System.out.println("              CARRERA ESTANDAR " + raceName.toUpperCase());
		System.out.println("-------------------------------------------------------------\n");

		for (CarInRace carInRace : carsInRace) {
			System.out.println(carInRace.getCar().getBrand() + " " + carInRace.getCar().getModel() + "\n\t"
					+ carInRace.getDistance() + " Km");
		}

		for (CarInRace carInRace : carsInRace) {
			if (podium <= 3) {
				puntuationAccumulator -= 10;
				carInRace.setPuntuation(carInRace.getPuntuation() + puntuationAccumulator);
				System.out.println("\n\tPUESTO Nº" + podium + " : " + carInRace.getCar().getBrand() + " "
						+ carInRace.getCar().getModel() + "\n\tDistancia: " + carInRace.getDistance() + " Km"
						+ "\n\tPuntuación: "
						+ puntuationAccumulator);
				podium++;
			}
		}
	}
	
}
