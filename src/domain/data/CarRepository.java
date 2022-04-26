package domain.data;

import java.util.List;

import domain.entities.Car;

public interface CarRepository {

	public List<Car> getCars(String filename);

	public void saveCars(String filename, List<Car> cars);
}
