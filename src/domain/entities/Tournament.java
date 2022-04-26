package domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {

	private String tournamentName;
	private List<Race> races = new ArrayList<>();
	private List<Garage> garages = new ArrayList<>();

	public Tournament(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public Tournament(String tournamentName, List<Race> races, List<Garage> garages) {
		this.tournamentName = tournamentName;
		this.races = races;
		this.garages = garages;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public List<Race> getRaces() {
		return races;
	}

	public List<Garage> getGarages() {
		return garages;
	}

	public void addRace(Race race) {
		races.add(race);
	}

	public void addGarage(Garage garage) {
		garages.add(garage);
	}

	public void removeTournamentRace(Race race) {
		races.remove(race);
	}

	@Override
	public String toString() {
		return this.tournamentName;
	}

	public void startTournament() {

		List<CarInRace> carsInRace = Competitor.tournamentAddCarsInRace(this);

		// for (CarInRace carInRace : carsInRace) {
		// carInRace.setPuntuation(0);
		// }

		for (Race race : races) {
			race.carsInRace = carsInRace;
			race.startRace();
		}

		System.out.println("\n-------------------------------------------------------------");
		System.out.println("             CLASIFICACIÓN FINAL " + tournamentName.toUpperCase());
		System.out.println("-------------------------------------------------------------\n");

		Collections.sort(carsInRace, new PuntuationComparator().reversed());

		for (CarInRace carInRace : carsInRace) {
			System.out.println("\t" + carInRace.getCar().getBrand() + " " + carInRace.getCar().getModel()
					+ "\n\tPuntuación: "
					+ carInRace.getPuntuation() + " PTS");
		}

		if (carsInRace.get(0).getPuntuation() == carsInRace.get(1).getPuntuation()) {
			System.out.println("\n" + carsInRace.get(0).getCar().getBrand().toUpperCase() + " "
					+ carsInRace.get(0).getCar().getModel().toUpperCase() + " Y "
					+ carsInRace.get(1).getCar().getBrand().toUpperCase() + " "
					+ carsInRace.get(1).getCar().getModel().toUpperCase() + " COMPARTEN EL PREMIO DEL TORNEO");
		} else {
			System.out.println("\n" + carsInRace.get(0).getCar().getBrand().toUpperCase() + " "
					+ carsInRace.get(0).getCar().getModel().toUpperCase() + " GANADOR ABSOLUTO DEL TORNEO");
		}
	}
}


