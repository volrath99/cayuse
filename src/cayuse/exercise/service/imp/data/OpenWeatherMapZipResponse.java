package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OpenWeatherMapZipResponse {
	private OpenWeatherMapCoord coord;
	@JsonIgnore
	private Object weather;
	@JsonIgnore
	private String base;
	private OpenWeatherMapMain main;
	@JsonIgnore
	private int visibility;
	@JsonIgnore
	private Object wind;
	@JsonIgnore
	private Object clouds;
	@JsonIgnore
	private Object rain;
	@JsonIgnore
	private int dt;
	@JsonIgnore
	private Object sys;
	@JsonIgnore
	private int id;
	private String name;
	@JsonIgnore
	private int cod;

	public String getName() {
		return name;
	}

	public OpenWeatherMapMain getMain() {
		return main;
	}

	public OpenWeatherMapCoord getCoord() {
		return coord;
	}

	public void setCoord(OpenWeatherMapCoord coord) {
		this.coord = coord;
	}

	public void setWeather(Object weather) {
		this.weather = weather;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public void setMain(OpenWeatherMapMain main) {
		this.main = main;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public void setWind(Object wind) {
		this.wind = wind;
	}

	public void setClouds(Object clouds) {
		this.clouds = clouds;
	}

	public void setRain(Object rain) {
		this.rain = rain;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public void setSys(Object sys) {
		this.sys = sys;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
}
