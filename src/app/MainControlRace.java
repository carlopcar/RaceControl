package app;

import java.util.ArrayList;
import java.util.List;

import data.CarRepositoryImp;
import data.FileDataImp;
import data.GarageRepositoryImp;
import data.RaceRepositoryImp;
import data.TournamentRepositoryImp;
import domain.entities.Car;
import domain.entities.Competitor;
import domain.entities.EliminationRace;
import domain.entities.EstandarRace;
import domain.entities.Garage;
import domain.entities.Race;
import domain.entities.Tournament;
import util.Input;

public class MainControlRace {

	private static CarRepositoryImp carRepositoryImp = new CarRepositoryImp(FileDataImp.getInstance());
	private static GarageRepositoryImp garageRepositoryImp = new GarageRepositoryImp(FileDataImp.getInstance());
	private static RaceRepositoryImp raceRepositoryImp = new RaceRepositoryImp(FileDataImp.getInstance());
	private static TournamentRepositoryImp tournamentRepositoryImp = new TournamentRepositoryImp(
			FileDataImp.getInstance());

	private static final String CAR_FILENAME = "Cars.json";
	private static final String GARAGE_FILENAME = "Garages.json";
	private static final String RACE_FILENAME = "Races.json";
	private static final String TOURNAMENT_FILENAME = "Tournaments.json";

	private static List<Car> cars;
	private static List<Garage> garages;
	private static List<Tournament> tournaments;
	private static List<Race> races;

	public static void main(String[] args) {

		cars = carRepositoryImp.getCars(CAR_FILENAME);
		if (cars == null) {
			cars = new ArrayList<>();
		}

		garages = garageRepositoryImp.getGarages(GARAGE_FILENAME);
		if (garages == null) {
			garages = new ArrayList<>();
		}

		races = raceRepositoryImp.getRaces(RACE_FILENAME);
		if (races == null) {
			races = new ArrayList<>();
		}

		tournaments = tournamentRepositoryImp.getTournaments(TOURNAMENT_FILENAME);
		if (tournaments == null) {
			tournaments = new ArrayList<>();
		}

		menu();
	}

	public static void createCar() {

		String brand = Input.inputString("Marca del coche: ");
		String model = Input.inputString("Modelo del coche: ");
		Car car = new Car(brand, model);
		addCar(car);
	}

	public static void createGarage() {

		String garageName = Input.inputString("Nombre de la escuder�a: ");
		Garage garage = new Garage(garageName);
		addGarage(garage);
	}

	public static void createRace() {

		String raceName = Input.inputString("Nombre de la carrera:");
		String raceType = Input.inputString("Tipo de carrera (ESTANDAR/ELIMINACION):");

		if (raceType.equalsIgnoreCase("ESTANDAR")) {
			float raceDuration = Input.inputFloat("�Cuantas horas durar� esta carrera?:");
			Race race = new EstandarRace(raceName, raceType, raceDuration);
			addRace(race);

		} else if (raceType.equalsIgnoreCase("ELIMINACION")) {
			Race race = new EliminationRace(raceName, raceType);
			addRace(race);

		} else {
			System.out.println("Debe introducir correctamente el tipo de carrera");
		}
	}

	public static void createTournament() {
		String tournamentName = Input.inputString("Nombre del torneo:");
		Tournament tournament = new Tournament(tournamentName);
		addTournament(tournament);
	}

	public static void addCar(Car car) {
		cars.add(car);
		carRepositoryImp.saveCars(CAR_FILENAME, cars);
	}

	public static List<Car> getCars() {
		return cars;
	}

	public static Car selectedCar(int index) {
		return cars.get(index - 1);
	}

	public static void listCars() {
		for (int i = 0; i < getCars().size(); i++) {
			System.out.println(i + 1 + ". " + getCars().get(i).toString());
		}
	}

	private static void addGarage(Garage garage) {
		garages.add(garage);
		garageRepositoryImp.saveGarage(GARAGE_FILENAME, garages);
	}

	public static List<Garage> getGarages() {
		return garages;
	}

	public static Garage selectedGarage(int index) {
		return garages.get(index - 1);
	}

	public static void listGarages() {
		for (int i = 0; i < getGarages().size(); i++) {
			System.out.println(i + 1 + ". " + getGarages().get(i).toString());
		}
	}

	public static void garagesInformation() {

		byte option = 0;

		if (!garages.isEmpty()) {

			listGarages();
			System.out.println("Escoge una escuder�a para ver su informaci�n:");
			option = Input.inputByte();

			Garage garageSelected = selectedGarage(option);
			if (!garageSelected.getCars().isEmpty()) {
				for (int i = 0; i < garageSelected.getCars().size(); i++) {
					System.out.println(garageSelected.getCars().get(i).toString());
				}
			} else {
				System.out.println("No hay coches asignados a esta escuder�a");
			}

		} else {
			System.out.println("Todav�a no has creado ning�na escuder�a");
		}
	}

	public static void addCarToGarage() {

		byte option = 0;

		if (!getCars().isEmpty()) {

			listCars();
			System.out.println("Escoge un coche:");
			option = Input.inputByte();

			Car carSelected = selectedCar(option);

			if (!getGarages().isEmpty()) {

				listGarages();
				System.out.println("Escoge la escuder�a a la que deseas a�adir el coche:");
				option = Input.inputByte();

				Garage garageSelected = selectedGarage(option);
				garageSelected.addCar(carSelected);
				getCars().remove(carSelected);
				carRepositoryImp.saveCars(CAR_FILENAME, cars);
				garageRepositoryImp.saveGarage(GARAGE_FILENAME, garages);

			} else {
				System.out.println("Todav�a no has creado ninguna escuder�a, crea una para poder continuar");
			}
		} else {
			System.out.println("No existe ning�n coche para escoger, debe crear al menos un coche");
		}
	}

	public static void removeCarToGarage() {

		byte option = 0;

		if (!garages.isEmpty()) {

			listGarages();
			System.out.println("Escoge una escuder�a:");
			option = Input.inputByte();

			Garage garageSelected = selectedGarage(option);

			if (!garageSelected.getCars().isEmpty()) {

				for (int i = 0; i < garageSelected.getCars().size(); i++) {
					System.out.println(i + 1 + ". " + garageSelected.getCars().get(i).toString());
				}
				System.out.println("Escoge un coche:");
				option = Input.inputByte();

				Car carSelected = garageSelected.selectedCarInGarage(option);
				carSelected.setSticker(null);
				cars.add(carSelected);
				garageSelected.getCars().remove(carSelected);
				carRepositoryImp.saveCars(CAR_FILENAME, cars);
				garageRepositoryImp.saveGarage(GARAGE_FILENAME, garages);

			} else {
				System.out.println("Esta escuder�a ya est� vac�a");
			}
		} else {
			System.out.println("No existe ning�na escuder�a para escoger, debe crear al menos una");
		}
	}

	private static void addRace(Race race) {
		races.add(race);
		raceRepositoryImp.saveRaces(RACE_FILENAME, races);
	}

	public static List<Race> getRaces() {
		return races;
	}

	public static Race selectedRace(int index) {
		return races.get(index - 1);
	}

	public static void listRaces() {
		for (int i = 0; i < races.size(); i++) {
			System.out.println(i + 1 + ". " + races.get(i).toString());
		}
	}

	public static void addGarageToRace() {

		byte option = 0;

		if (!garages.isEmpty()) {

			listGarages();
			System.out.println("Escoge una escuder�a:");
			option = Input.inputByte();


			Garage garageSelected = selectedGarage(option);

			if (!garageSelected.getCars().isEmpty()) {

				if (!races.isEmpty()) {

					listRaces();
					System.out.println("Escoge la carrera en la que deseas inscribir la escuder�a:");
					option = Input.inputByte();

					Race raceSelected = selectedRace(option);
					raceSelected.addGarage(garageSelected);

				} else {
					System.out.println("Todav�a no has creado ning�na carrera, crea una para poder continuar");
				}
			} else {
				System.out.println("La escuder�a elegida no tiene coches asignados y no puede asignarse a un torneo");
			}
		} else {
			System.out.println("No existe ning�na escuder�a para escoger, debe crear al menos una");
		}
	}

	private static void simulatedRace() {

		byte option = 0;

		if (!races.isEmpty()) {

			listRaces();
			System.out.println("Escoge la carrera que deseas simular");
			option = Input.inputByte();

			Race raceSelected = selectedRace(option);

			if (!raceSelected.getGarages().isEmpty()) {
				Competitor.addCarInRace(raceSelected);
				raceSelected.startRace();
			} else {
				System.out.println("Esta carrera no contiene ning�n participante, debes a�adirlos antes de correr");
			}
		} else {
			System.out.println("Todav�a no has creado ning�na carrera, crea una para poder continuar");
		}
	}

	private static void addTournament(Tournament tournament) {
		tournaments.add(tournament);
		tournamentRepositoryImp.saveTournament(TOURNAMENT_FILENAME, tournaments);
	}

	public static List<Tournament> getTournaments() {
		return tournaments;
	}

	public static Tournament selectedTournament(int index) {
		return tournaments.get(index - 1);
	}

	public static void listTournaments() {
		for (int i = 0; i < tournaments.size(); i++) {
			System.out.println(i + 1 + ". " + tournaments.get(i).toString());
		}
	}

	public static void tournamentInformation() {
		
		byte option = 0;
		
		if (!tournaments.isEmpty()) {

			listTournaments();
			System.out.println("Escoge un torneo para ver su informaci�n:");
			option = Input.inputByte();

			Tournament tournamentSelected = selectedTournament(option);
			if (!tournamentSelected.getRaces().isEmpty()) {
				for (int i = 0; i < tournamentSelected.getRaces().size(); i++) {
					System.out.println("Carrera " + (i + 1) + ": " + tournamentSelected.getRaces().get(i).toString());
				}
			} else {
				System.out.println("No hay carreras asignadas a este torneo");
			}

			if (!tournamentSelected.getGarages().isEmpty()) {
				for (int i = 0; i < tournamentSelected.getGarages().size(); i++) {
					System.out.println(tournamentSelected.getGarages().get(i).toString());
				}
			} else {
				System.out.println("No hay escuder�as asignados a este torneo");
			}

		} else {
			System.out.println("Todav�a no has creado ning�n torneo");
		}
	}

	public static void addRaceToTournament() {

		byte option = 0;

		if (!races.isEmpty()) {

			listRaces();
			System.out.println("Escoge una carrera:");
			option = Input.inputByte();

			Race raceSelected = selectedRace(option);

			if (!tournaments.isEmpty()) {

				listTournaments();
				System.out.println("Escoge el torneo al que deseas a�adir la carrera:");
				option = Input.inputByte();

				Tournament tournamentSelected = selectedTournament(option);
				tournamentSelected.addRace(raceSelected);
				tournamentRepositoryImp.saveTournament(TOURNAMENT_FILENAME, tournaments);

			} else {
				System.out.println("Todav�a no has creado ning�n torneo, crea uno para poder continuar");
			}
		} else {
			System.out.println("No existe ning�na carrera para escoger, debe crear al menos una carrera");
		}
	}

	public static void addGarageToTournament() {

		byte option = 0;

		if (!garages.isEmpty()) {

			listGarages();
			System.out.println("Escoge una escuder�a:");
			option = Input.inputByte();

			Garage garageSelected = selectedGarage(option);

			if (!garageSelected.getCars().isEmpty()) {

				if (!tournaments.isEmpty()) {

					listTournaments();
					System.out.println("Escoge el torneo en el que deseas inscribir la escuder�a:");
				option = Input.inputByte();

				Tournament tournamentSelected = selectedTournament(option);
				tournamentSelected.addGarage(garageSelected);
				tournamentRepositoryImp.saveTournament(TOURNAMENT_FILENAME, tournaments);

			} else {
				System.out.println("Todav�a no has creado ning�n torneo, crea uno para poder continuar");
			}
			} else {
				System.out
						.println("La escuder�a elegida no tiene coches asignados y no puede asignarse a un torneo");
			}
		} else {
			System.out.println("No existe ning�na escuder�a para escoger, debe crear al menos uno");
		}
	}

	public static void simulatedTournament() {

		byte option = 0;

		if (!tournaments.isEmpty()) {

			listTournaments();
			System.out.println("Escoge el torneo que deseas simular");
			option = Input.inputByte();

			Tournament tournamentSelected = selectedTournament(option);
			if (!tournamentSelected.getRaces().isEmpty()) {
				if (!tournamentSelected.getGarages().isEmpty()) {
					tournamentSelected.startTournament();
				} else {
					System.out.println(
							"El torneo elegido no contiene ninguna escuder�a, debes asignarle al menos una para poder simularlo");
				}

			} else {
				System.out.println(
						"El torneo elegido no contiene ninguna carrera, debes asignarle al menos una para poder simularlo");
			}
		} else {
			System.out.println("Todav�a no has creado ning�n torneo, crea uno para poder continuar");
		}
	}

	public static void menuCar() {

		byte option = 0;

		do {
			System.out.println("\n-------------------------------------------------------------");
			System.out.println("                      OPCIONES DE COCHE                      ");
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("1.Ver mis coches\n" + "2.Crear coche\n" + "3.A�adir coche a escuder�a\n"
					+ "4.Volver al men� inicial");
			System.out.print("-------------------------------------------------------------");

			System.out.print("\nEscoge la opci�n deseada:");
			option = Input.inputByte();

			if (option != 4) {

				switch (option) {
				case 1:
					listCars();
					break;
				case 2:
					createCar();
					break;
				case 3:
					addCarToGarage();
					break;

				default:
					System.out.print("\n�INTRODUCE UNA OPCI�N V�LIDA!\n");
					break;
				}
			}
		} while (option != 4);
	}

	public static void menuGarage() {

		byte option = 0;

		do {
			System.out.println("\n-------------------------------------------------------------");
			System.out.println("                      OPCIONES DE GARAJE                     ");
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("1.Ver informaci�n de las escuder�as\n" + "2.Crear escuder�a\n"
					+ "3.A�adir coche a escuder�a\n" + "4.Quitar coche de escuder�a\n" + "5.Volver al men� inicial");
			System.out.print("-------------------------------------------------------------");

			System.out.print("\nEscoge la opci�n deseada:");
			option = Input.inputByte();

			if (option != 5) {

				switch (option) {
				case 1:
					garagesInformation();
					break;
				case 2:
					createGarage();
					break;
				case 3:
					addCarToGarage();
					break;
				case 4:
					removeCarToGarage();
					break;
				default:
					System.out.print("\n�INTRODUCE UNA OPCI�N V�LIDA!\n");
					break;
				}
			}

		} while (option != 5);
	}

	public static void menuRace() {

		byte option = 0;

		do {
			System.out.println("\n-------------------------------------------------------------");
			System.out.println("                      OPCIONES DE CARRERA                     ");
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("1.Listar carreras\n" + "2.Crear carrera\n" + "3.A�adir participantes a carrera\n"
					+ "4.Simular Carrera\n" + "5.Volver al men� inicial");
			System.out.print("-------------------------------------------------------------");

			System.out.print("\nEscoge la opci�n deseada:");
			option = Input.inputByte();

			if (option != 5) {

				switch (option) {
				case 1:
					listRaces();
					break;
				case 2:
					createRace();
					break;
				case 3:
					addGarageToRace();
					break;
				case 4:
					simulatedRace();
					break;
				default:
					System.out.print("\n�INTRODUCE UNA OPCI�N V�LIDA!\n");
					break;
				}
			}

		} while (option != 5);
	}

	public static void menuTournament() {

		byte option = 0;

		do {
			System.out.println("\n-------------------------------------------------------------");
			System.out.println("                      OPCIONES DE TORNEO                     ");
			System.out.println("-------------------------------------------------------------\n");
			System.out.println(
					"1.Ver informaci�n de los torneos\n" + "2.Crear torneo\n" + "3.A�adir escuder�a a torneo\n"
							+ "4.A�adir carrera a torneo\n" + "5.Simular torneo\n" + "6.Volver al men� inicial");
			System.out.print("-------------------------------------------------------------");

			System.out.print("\nEscoge la opci�n deseada:");
			option = Input.inputByte();

			if (option != 6) {

				switch (option) {
				case 1:
					tournamentInformation();
					break;
				case 2:
					createTournament();
					break;
				case 3:
					addGarageToTournament();
					break;
				case 4:
					addRaceToTournament();
					break;
				case 5:
					simulatedTournament();
					break;
				default:
					System.out.print("\n�INTRODUCE UNA OPCI�N V�LIDA!\n");
					break;
				}
			}
		} while (option != 6);
	}

	public static void menu() {

		byte option = 0;

		do {
			System.out.println("\n-------------------------------------------------------------");
			System.out.println("                        RACE CONTROL                         ");
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("1.Gestionar mis coches\n" + "2.Gestionar mis escuder�as\n"
					+ "3.Gestionar mis carreras\n"
					+ "4.Gestionar mis torneos\n" + "5.Salir del simulador");
			System.out.print("-------------------------------------------------------------");

				System.out.print("\nEscoge la opci�n deseada:");
				option = Input.inputByte();

			if (option != 5) {

				switch (option) {
				case 1:
					menuCar();
					break;
				case 2:
					menuGarage();
					break;
				case 3:
					menuRace();
					break;
				case 4:
					menuTournament();
					break;
				default:
					System.out.print("\n�INTRODUCE UNA OPCI�N V�LIDA!\n");
					break;
				}
			}
		} while (option != 5);
	}
}
