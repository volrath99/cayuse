package main.cayuse.exercise.service.data.api.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleTimeZoneResponse {
	private String status;
	private String timeZoneName;
	private String errorMessage;

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public String getStatus() {
		return status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
