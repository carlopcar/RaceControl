package data;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.data.CarRepository;
import domain.data.FileData;
import domain.entities.Car;

public class CarRepositoryImp implements CarRepository {

	private FileData fileData;

	public CarRepositoryImp(FileData fileData) {
		this.fileData = fileData;
	}

	@Override
	public void saveCars(String fileName, List<Car> cars) {

		String carsJson = new Gson().toJson(cars);
		fileData.write(fileName, carsJson);

	}

	@Override
	public List<Car> getCars(String fileName) {
		
		String json = fileData.read(fileName);
		Type type = new TypeToken<List<Car>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

}
