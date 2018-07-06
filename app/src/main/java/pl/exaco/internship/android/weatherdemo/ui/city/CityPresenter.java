package pl.exaco.internship.android.weatherdemo.ui.city;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.service.ICitiesManager;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.RequestCallback;

public class CityPresenter implements CityContract.Presenter {

	private final CityContract.View view;
	private final ICitiesManager citiesManager;

	CityPresenter(CityContract.View view, IServiceFactory serviceFactory) {
		this.view = view;
		this.citiesManager = serviceFactory.getCitiesManager();
	}


	@Override
	public void loadCities() {
		citiesManager.loadCities(new RequestCallback<List<City>>() {
			@Override
			public void onSuccess(List<City> data) {
				view.onCitiesLoaded(data);
			}

			@Override
			public void onError(Throwable throwable) {

			}// ignore
		});
	}

	@Override
	public void addCity(City city) {
		citiesManager.addCity(city);
	}
}
