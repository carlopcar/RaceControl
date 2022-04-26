package domain.data;

import java.util.List;

import domain.entities.Garage;

public interface GarageRepository {

	public List<Garage> getGarages(String filename);

	public void saveGarage(String filename, List<Garage> garages);
}
