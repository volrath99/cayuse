package cayuse.exercise.service.imp.data;

import java.util.List;

public class GoogleElevationResponse {
	public List<GoogleElevationResults> results;
	public String status;

	public List<GoogleElevationResults> getResults() {
		return results;
	}

}
