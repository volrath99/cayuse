package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class GoogleRetrieverAbstract {
	private static final String URI = "https://maps.googleapis.com/maps/api";

	private final Client client;
	private final String key;

	public GoogleRetrieverAbstract(String key) {
		client = ClientBuilder.newClient();
		this.key = key;
	}

	abstract String getPath();

	protected WebTarget getTarget() {
		return client.target(URI).path(getPath()).queryParam("key", key);
	}

}
