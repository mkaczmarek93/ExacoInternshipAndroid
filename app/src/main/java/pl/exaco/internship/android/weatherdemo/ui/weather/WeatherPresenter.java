package pl.exaco.internship.android.weatherdemo.ui.weather;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.service.ICitiesManager;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.IWeatherManager;
import pl.exaco.internship.android.weatherdemo.service.RequestCallback;

public class WeatherPresenter implements WeatherContract.Presenter {

	private final WeatherContract.View view;
	private final IWeatherManager weatherManager;
	private final ICitiesManager citiesManager;

	public WeatherPresenter(WeatherContract.View view, IServiceFactory serviceFactory) {
		this.view = view;
		this.weatherManager = serviceFactory.getWeatherManager();
		this.citiesManager = serviceFactory.getCitiesManager();
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
}
