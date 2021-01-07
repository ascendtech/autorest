package us.ascendtech.gwt.autorest.client;

public interface AutoRestCallback<T> {

	void onError(int statusCode, String status, String errorBody);
}
