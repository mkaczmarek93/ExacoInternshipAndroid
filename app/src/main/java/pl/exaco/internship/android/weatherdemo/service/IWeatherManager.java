package pl.exaco.internship.android.weatherdemo.service;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.model.CityWeather;

public interface IWeatherManager {

	void getWeatherForCities(List<City> cities, RequestCallback<List<CityWeather>> callback);
}
