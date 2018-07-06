package pl.exaco.internship.android.weatherdemo.service;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.City;

public interface ICitiesManager {

	List<City> getSavedCities();

	void loadCities(RequestCallback<List<City>> callback);

	void addCity(City city);
}
