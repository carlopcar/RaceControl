package data;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import domain.entities.Race;

public class RaceDeserializer implements JsonDeserializer<Race> {

	private String raceTypeElementName;
	private Gson gson;
	private Map<String, Class<? extends Race>> raceTypeRegistry;

	public RaceDeserializer(String raceTypeElementName) {
		this.raceTypeElementName = raceTypeElementName;
        this.gson = new Gson();
		this.raceTypeRegistry = new HashMap<>();
    }

	public void registerRaceType(String raceTypeName, Class<? extends Race> raceType) {
		raceTypeRegistry.put(raceTypeName, raceType);
	}

	@Override
	public Race deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject raceObject = json.getAsJsonObject();
		JsonElement raceTypeElement = raceObject.get(raceTypeElementName);

		Class<? extends Race> raceType = raceTypeRegistry.get(raceTypeElement.getAsString().toUpperCase());
		return gson.fromJson(raceObject, raceType);
	}
}
