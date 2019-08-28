package cayuse.exercise.service.imp;

import java.util.concurrent.CompletableFuture;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cayuse.exercise.conf.ConfigProperties;
import cayuse.exercise.service.TimeZoneRetriever;
import cayuse.exercise.service.data.api.google.GoogleTimeZoneResponse;

@Service
public class GoogleTimeZoneRetriever extends GoogleRetrieverAbstract implements TimeZoneRetriever {

	private static final Logger LOGGER = LogManager.getLogger(GoogleTimeZoneRetriever.class);
	private static final String PATH = "/timezone/json";

	public GoogleTimeZoneRetriever(Client client, @Autowired ConfigProperties configProperties) {
		super(client, configProperties);
	}

	@Async
	@Override
	public CompletableFuture<String> getTimeZone(double latitude, double longitude) {
		WebTarget target = getTarget().queryParam("location", latitude + "," + longitude).queryParam("timestamp", 0);
		GoogleTimeZoneResponse response = target.request(MediaType.APPLICATION_JSON).get(GoogleTimeZoneResponse.class);

		if (!"OK".equals(response.getStatus())) {
			String errorMsg = "Request [" + target.getUri() + "] failed: " + response.getErrorMessage();
			LOGGER.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}

		return CompletableFuture.completedFuture(response.getTimeZoneName());
	}

	@Override
	String getPath() {
		return PATH;
	}

}
