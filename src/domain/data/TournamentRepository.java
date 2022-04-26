package domain.data;

import java.util.List;

import domain.entities.Tournament;

public interface TournamentRepository {

	public List<Tournament> getTournaments(String filename);

	public void saveTournament(String filename, List<Tournament> tournaments);
}
