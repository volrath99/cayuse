package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OpenWeatherMapMain {
	private double temp;
	@JsonIgnore
	private int pressure;
	@JsonIgnore
	private int humidity;
	@JsonIgnore
	private double temp_min;
	@JsonIgnore
	private double temp_max;
	@JsonIgnore
	private double sea_level;
	@JsonIgnore
	private double grnd_level;
	
	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}

	public void setSea_level(double sea_level) {
		this.sea_level = sea_level;
	}

	public void setGrnd_level(double grnd_level) {
		this.grnd_level = grnd_level;
	}

}
