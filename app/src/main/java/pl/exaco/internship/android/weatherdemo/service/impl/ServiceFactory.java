package pl.exaco.internship.android.weatherdemo.service.impl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.exaco.internship.android.weatherdemo.service.ICitiesManager;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.IWeatherManager;

@EBean(scope = EBean.Scope.Singleton)
public class ServiceFactory implements IServiceFactory {

	@Bean(CitiesManager.class)
	ICitiesManager citiesManager;

	@Bean(WeatherManager.class)
	IWeatherManager weatherManager;

	@Override
	public ICitiesManager getCitiesManager() {
		return citiesManager;
	}

	@Override
	public IWeatherManager getWeatherManager() {
		return weatherManager;
	}
}
