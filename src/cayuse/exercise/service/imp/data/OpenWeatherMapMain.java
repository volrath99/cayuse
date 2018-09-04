package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OpenWeatherMapMain {
	public double temp;
	@JsonIgnore
	public int pressure;
	@JsonIgnore
	public int humidity;
	@JsonIgnore
	public double temp_min;
	@JsonIgnore
	public double temp_max;
	
	public double getTemp() {
		return temp;
	}
}
