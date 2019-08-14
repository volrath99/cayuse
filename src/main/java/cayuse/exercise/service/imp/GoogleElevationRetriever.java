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
import cayuse.exercise.service.ElevationRetriever;
import cayuse.exercise.service.data.api.google.GoogleElevationResponse;

@Service
public class GoogleElevationRetriever extends GoogleRetrieverAbstract implements ElevationRetriever {
	private static final Logger LOGGER = LogManager.getLogger(GoogleElevationRetriever.class);
	private static final String PATH = "/elevation/json";

	public GoogleElevationRetriever(@Autowired Client client, @Autowired ConfigProperties configProperties) {
		super(client, configProperties);
	}

	@Async
	@Override
	public CompletableFuture<Double> getElevation(double latitude, double longitude) {
		WebTarget target = getTarget().queryParam("locations", latitude + "," + longitude);
		GoogleElevationResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(GoogleElevationResponse.class);

		if (!"OK".equals(response.getStatus())) {
			String errorMsg = "Request [" + target.getUri() + "] failed: " + response.getErrorMessage();
			LOGGER.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}

		return CompletableFuture.completedFuture(response.getResults().get(0).getElevation());
	}

	@Override
	String getPath() {
		return PATH;
	}

}
