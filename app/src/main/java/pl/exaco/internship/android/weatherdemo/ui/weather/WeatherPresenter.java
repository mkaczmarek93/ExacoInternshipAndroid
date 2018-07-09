package pl.exaco.internship.android.weatherdemo.ui.weather;

import android.util.Log;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.service.ICitiesManager;
import pl.exaco.internship.android.weatherdemo.service.IFutureWeatherManager;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.IWeatherManager;
import pl.exaco.internship.android.weatherdemo.service.RequestCallback;

public class WeatherPresenter implements WeatherContract.Presenter {
	private static final String TAG = WeatherPresenter.class.getSimpleName();


	private final WeatherContract.View view;
	private final IWeatherManager weatherManager;
	private final ICitiesManager citiesManager;
	private final IFutureWeatherManager futureWeatherManager;

	public WeatherPresenter(WeatherContract.View view, IServiceFactory serviceFactory) {
		this.view = view;
		this.weatherManager = serviceFactory.getWeatherManager();
		this.citiesManager = serviceFactory.getCitiesManager();
		this.futureWeatherManager = serviceFactory.getFutureWeatherManager();
	}

	@Override
	public void getWeather() {
		weatherManager.getWeatherForCities(citiesManager.getSavedCities(), new RequestCallback<List<CityWeather>>() {
			@Override
			public void onSuccess(List<CityWeather> data) {
				view.onSuccess(data);
			}

			@Override
			public void onError(Throwable throwable) {
				view.onFailure();
			}
		});
	}

	@Override
	public void getFutureWeather(Integer id) {
		futureWeatherManager.getFutureWeatherByCity(id, new RequestCallback<List<CityWeather>>() {
			@Override
			public void onSuccess(List<CityWeather> data) {
				view.onSuccess(data);
				Log.e(TAG, "Success future weather");
			}

			@Override
			public void onError(Throwable throwable) {
				Log.e(TAG, "Future weather failure");
			}
		});
	}
}
