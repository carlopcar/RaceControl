package domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Garage {


	private String garageName;
	private List<Car> cars = new ArrayList<>();

	public Garage(String garageName) {
		this.garageName = garageName;
	}

	public Garage(String garageName, List<Car> cars) {
		this.garageName = garageName;
		this.cars = cars;
	}

	public String getGarageName() {
		return garageName;
	}

	public List<Car> getCars() {
		return cars;
	}

	public int garageCarsSize() {
		return cars.size();
	}

	public void addCar(Car car) {
		cars.add(car);
		car.setSticker(garageName);
	}

	public void removeGarageCar(Car car) {
		cars.remove(car);
	}

	@Override
	public String toString() {
		return garageName;
	}

	public Car selectedCarInGarage(int index) {
		return cars.get(index - 1);
	}

	public Car randomCar() {

		Random r = new Random();
		
		List<Car> cars = new ArrayList<>(this.cars);

		int index = r.nextInt(garageCarsSize());

		return cars.get(index);
	}


}
