package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OpenWeatherMapZipResponse {
	public OpenWeatherMapCoord coord;
	@JsonIgnore
	public Object weather;
	@JsonIgnore
	public String base;
	public OpenWeatherMapMain main;
	@JsonIgnore
	public int visibility;
	@JsonIgnore
	public Object wind;
	@JsonIgnore
	public Object clouds;
	@JsonIgnore
	public int dt;
	@JsonIgnore
	public Object sys;
	@JsonIgnore
	public int id;

	public String name;

	@JsonIgnore
	public int cod;

	public String getName() {
		return name;
	}

	public OpenWeatherMapMain getMain() {
		return main;
	}

	public OpenWeatherMapCoord getCoord() {
		return coord;
	}
}
