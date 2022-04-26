package domain.data;

import java.util.List;

import domain.entities.Race;

public interface RaceRepository {

	public List<Race> getRaces(String filename);

	public void saveRaces(String filename, List<Race> races);
}
