package us.ascendtech.client.services;

import elemental2.dom.DomGlobal;

/**
 * Created by Payam Meyer on 5/13/15.
 * @author pmeyer
 */
public class ServiceProvider {

	private static final ServiceProvider serviceProvider = new ServiceProvider();

	public static ServiceProvider get() {
		return serviceProvider;
	}

	private final ToDoServiceClient todoServiceClient;

	private ServiceProvider() {
		String baseUrl = DomGlobal.window.location.protocol + "//" + DomGlobal.window.location.host;
		todoServiceClient = new ToDoServiceClientRestServiceModel(baseUrl);
	}

	public ToDoServiceClient getTodoServiceClient() {
		return todoServiceClient;
	}

}
