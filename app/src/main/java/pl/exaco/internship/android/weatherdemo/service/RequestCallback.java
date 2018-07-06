package pl.exaco.internship.android.weatherdemo.service;

public interface RequestCallback<T> {

	void onSuccess(T data);

	void onError(Throwable throwable);
}
