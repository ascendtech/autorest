package us.ascendtech.gwt.autorest.client;

public class RestServiceModel {
	protected final RequestResourceBuilder path;

	public RestServiceModel(RequestResourceBuilder path) {
		this.path = path;
	}

	protected RequestResourceBuilder method(String method) {
		return path.method(method);
	}
}
