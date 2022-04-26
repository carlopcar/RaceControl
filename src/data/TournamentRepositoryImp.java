package data;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import domain.data.FileData;
import domain.data.TournamentRepository;
import domain.entities.EliminationRace;
import domain.entities.EstandarRace;
import domain.entities.Race;
import domain.entities.Tournament;

public class TournamentRepositoryImp implements TournamentRepository {

	private FileData fileData;

	public TournamentRepositoryImp(FileData fileData) {
		this.fileData = fileData;
	}

	@Override
	public List<Tournament> getTournaments(String fileName) {

		String json = fileData.read(fileName);

		RaceDeserializer deserializer = new RaceDeserializer("raceType");
		deserializer.registerRaceType("ESTANDAR", EstandarRace.class);
		deserializer.registerRaceType("ELIMINACION", EliminationRace.class);

		Gson gson = new GsonBuilder().registerTypeAdapter(Race.class, deserializer).create();

		return gson.fromJson(json, new TypeToken<List<Tournament>>() {
		}.getType());
		
	}

	@Override
	public void saveTournament(String fileName, List<Tournament> tournaments) {

		String tournamentJson = new Gson().toJson(tournaments);
		fileData.write(fileName, tournamentJson);

	}

}
