package us.ascendtech.gwt.autorest.client;

public class RestServiceModel {
	protected final String baseUrl;
	private final String servicePath;

	public RestServiceModel(String baseUrl, String servicePath) {
		this.baseUrl = baseUrl;
		this.servicePath = servicePath;
	}

	protected RequestResourceBuilder method(String method) {
		RequestResourceBuilder requestResourceBuilder = new RequestResourceBuilder(baseUrl);
		return requestResourceBuilder.method(method).path(servicePath);
	}
}
