package pl.exaco.internship.android.weatherdemo.db;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.City;

public interface IPrefDataStore {

	List<City> getSavedCities();

	void addNewCity(City city);
}
