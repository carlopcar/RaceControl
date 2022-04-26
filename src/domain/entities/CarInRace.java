package domain.entities;

public class CarInRace {

	private Car car;
	private int speedometer;
	private Double distance;
	private Integer puntuation;
	private static final int MAX_SPEED = 300;
	private static final int INICIAL_SPEED = 0;

	public CarInRace(Car car) {
		this.car = car;
		this.distance = 0.0;
		this.puntuation = 0;
	}

	public Car getCar() {
		return car;
	}

	public int getSpeedometer() {
		return speedometer;
	}

	public Double getDistance() {
		return distance;
	}

	public Integer getPuntuation() {
		return puntuation;
	}

	public void setSpeedometer(int speedometer) {
		this.speedometer = speedometer;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setPuntuation(Integer puntuation) {
		this.puntuation = puntuation;
	}

	public void acelerate() {

		if (MAX_SPEED > this.speedometer) {

			this.speedometer += 10;

		} else {

			this.speedometer = MAX_SPEED;

		}
	}

	public void brake() {

		if (INICIAL_SPEED < this.speedometer) {

			this.speedometer -= 10;

		} else {

			this.speedometer = INICIAL_SPEED;

		}
	}

	public void randomAcelerationBraking() {

		if (Math.round(Math.random()) == 0) {
			acelerate();
		} else {
			brake();
		}


	}

}
