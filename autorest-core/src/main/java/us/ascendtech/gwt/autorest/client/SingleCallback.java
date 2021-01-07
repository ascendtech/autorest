package us.ascendtech.gwt.autorest.client;

public interface SingleCallback<T> extends AutoRestCallback<T> {

	void onData(T data);

}
