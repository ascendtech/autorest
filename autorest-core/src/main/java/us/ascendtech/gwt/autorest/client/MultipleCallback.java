package us.ascendtech.gwt.autorest.client;

public interface MultipleCallback<T> extends AutoRestCallback<T> {

	void onData(T[] data);

}
