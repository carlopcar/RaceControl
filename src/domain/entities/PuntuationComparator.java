package domain.entities;

import java.util.Comparator;

public class PuntuationComparator implements Comparator<CarInRace> {

	@Override
	public int compare(CarInRace o1, CarInRace o2) {

		return o1.getPuntuation().compareTo(o2.getPuntuation());

	}

}
