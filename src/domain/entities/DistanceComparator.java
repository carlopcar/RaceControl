package domain.entities;

import java.util.Comparator;

public class DistanceComparator implements Comparator<CarInRace> {

	@Override
	public int compare(CarInRace o1, CarInRace o2) {

		return o1.getDistance().compareTo(o2.getDistance());

	}

}
