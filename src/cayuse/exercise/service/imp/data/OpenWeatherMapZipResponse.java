package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapZipResponse {
	private OpenWeatherMapCoord coord;
	private OpenWeatherMapMain main;
	private String name;

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

	public void setMain(OpenWeatherMapMain main) {
		this.main = main;
	}

	public void setName(String name) {
		this.name = name;
	}
}
