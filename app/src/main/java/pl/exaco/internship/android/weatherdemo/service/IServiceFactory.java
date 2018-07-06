package pl.exaco.internship.android.weatherdemo.service;

public interface IServiceFactory {

	ICitiesManager getCitiesManager();

	IWeatherManager getWeatherManager();
}
