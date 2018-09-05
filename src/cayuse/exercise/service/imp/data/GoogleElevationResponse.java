package cayuse.exercise.service.imp.data;

import java.util.List;

public class GoogleElevationResponse {
	private List<GoogleElevationResults> results;
	private String status;
	private String error_message;

	public List<GoogleElevationResults> getResults() {
		return results;
	}

	public String getStatus() {
		return status;
	}

	public String getErrorMessage() {
		return error_message;
	}

	public void setResults(List<GoogleElevationResults> results) {
		this.results = results;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public void setError_message(String error_message) {
		this.error_message = error_message;
	}


}
