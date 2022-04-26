package data;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import domain.data.FileData;
import domain.data.RaceRepository;
import domain.entities.EliminationRace;
import domain.entities.EstandarRace;
import domain.entities.Race;

public class RaceRepositoryImp implements RaceRepository {

	private FileData fileData;

	public RaceRepositoryImp(FileData fileData) {
		this.fileData = fileData;
	}

	@Override
	public List<Race> getRaces(String fileName) {

		String json = fileData.read(fileName);

		RaceDeserializer deserializer = new RaceDeserializer("raceType");
		deserializer.registerRaceType("ESTANDAR", EstandarRace.class);
		deserializer.registerRaceType("ELIMINACION", EliminationRace.class);

	    Gson gson = new GsonBuilder()
	      .registerTypeAdapter(Race.class, deserializer)
	      .create();

		return gson.fromJson(json, new TypeToken<List<Race>>() {
		}.getType());
	}

	@Override
	public void saveRaces(String fileName, List<Race> races) {

		Gson gson = new Gson();
		String raceJson = gson.toJson(races);
		fileData.write(fileName, raceJson);

	}

}
