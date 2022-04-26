package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Competitor {
 
	public static void addCarInRace(Race race) {

		race.carsInRace.clear();

		if (race.getGarages().size() == 1) {
			Garage garage = race.getGarages().iterator().next();
			for (Car car : garage.getCars()) {
				CarInRace carInRace = new CarInRace(car);
				race.getCarsInRace().add(carInRace);
			}

		} else {
			for (Garage garage : race.getGarages()) {
				CarInRace carInRace = new CarInRace(garage.randomCar());
				race.getCarsInRace().add(carInRace);
			}
		}
	}

	public static List<CarInRace> tournamentAddCarsInRace(Tournament tournament) {

		ArrayList<CarInRace> carsInRace = new ArrayList<>();
		
		if (tournament.getGarages().size() == 1) {
			Garage garage = tournament.getGarages().iterator().next();
			for (Car car : garage.getCars()) {
				CarInRace carInRace = new CarInRace(car);
				carsInRace.add(carInRace);
			}

		} else {
			for (Garage garage : tournament.getGarages()) {
				CarInRace carInRace = new CarInRace(garage.randomCar());
				carsInRace.add(carInRace);
			}
		}

		return carsInRace;
	}
}
