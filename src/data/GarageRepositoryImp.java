package data;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.data.FileData;
import domain.data.GarageRepository;
import domain.entities.Garage;

public class GarageRepositoryImp implements GarageRepository {

	private FileData fileData;

	public GarageRepositoryImp(FileData fileData) {
		this.fileData = fileData;
	}

	@Override
	public List<Garage> getGarages(String fileName) {

		String json = fileData.read(fileName);
		Type type = new TypeToken<List<Garage>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	@Override
	public void saveGarage(String fileName, List<Garage> garages) {

		String garageJson = new Gson().toJson(garages);
		fileData.write(fileName, garageJson);

	}

}
