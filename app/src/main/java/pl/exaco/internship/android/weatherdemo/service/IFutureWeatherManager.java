package pl.exaco.internship.android.weatherdemo.service;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.model.FutureWeather;

//Damian
public interface IFutureWeatherManager {


    void getFutureWeatherByCity(Integer cityId,  RequestCallback<List<CityWeather>> callback);


}
