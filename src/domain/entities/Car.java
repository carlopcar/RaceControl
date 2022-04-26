package domain.entities;

public class Car {

	private String brand;
	private String model;
	private String sticker;

	public Car(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}

	public Car(String brand, String model, String sticker) {
		this.brand = brand;
		this.model = model;
		this.sticker = sticker;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getSticker() {
		return sticker;
	}

	public void setSticker(String sticker) {
		this.sticker = sticker;
	}

	@Override
	public String toString() {
		return this.brand + " " + this.model;
	}

}
