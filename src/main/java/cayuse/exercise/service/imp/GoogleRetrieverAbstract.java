package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Autowired;

import cayuse.exercise.conf.ConfigProperties;

public abstract class GoogleRetrieverAbstract {

	private final Client client;
	private final String key;
	private final String url;

	public GoogleRetrieverAbstract(@Autowired Client client, @Autowired ConfigProperties configProperties) {
		this.client = client;
		key = configProperties.getGoogleApi().getApiKey();
		url = configProperties.getGoogleApi().getUrl();
	}

	abstract String getPath();

	protected WebTarget getTarget() {
		return client.target(url).path(getPath()).queryParam("key", key);
	}

}
