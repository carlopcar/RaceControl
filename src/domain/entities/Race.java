package domain.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Race {

	protected String raceName;
	protected String raceType;
	protected transient List<Garage> garages = new ArrayList<>();
	protected transient List<CarInRace> carsInRace = new ArrayList<>();
	protected transient List<CarInRace> carsInRaceElimination = new ArrayList<>();

	protected Race() {

	}

	protected Race(String raceName, String raceType) {
		this.raceName = raceName;
		this.raceType = raceType;
	}

	public abstract void startRace();

	protected String getRaceName() {
		return raceName;
	}

	public List<Garage> getGarages() {
		return garages;
	}

	public void addGarage(Garage garage) {
		garages.add(garage);
	}

	protected List<CarInRace> getCarsInRace() {
		return carsInRace;
	}

	@Override
	public String toString() {
		return raceName + " " + raceType;
	}

	protected void raceExecution() {

		for (CarInRace carInRace : carsInRace) {

			if (Math.floor(Math.random() * 6) == 0 || Math.floor(Math.random() * 6) == 5) {
				carInRace.brake();
			}
			if (carInRace.getSpeedometer() < 100) {
				carInRace.acelerate();
			}

			carInRace.randomAcelerationBraking();
			double distanceAccumulator = carInRace.getDistance();
			distanceAccumulator += Double.valueOf(carInRace.getSpeedometer() / 60);
			carInRace.setDistance(distanceAccumulator);

		}
	}
}
