package pl.exaco.internship.android.weatherdemo.service.impl;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.CitiesWeather;
import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.service.IWeatherManager;
import pl.exaco.internship.android.weatherdemo.service.RequestCallback;
import pl.exaco.internship.android.weatherdemo.service.api.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EBean(scope = EBean.Scope.Singleton)
public class WeatherManager extends BaseManager implements IWeatherManager {

	private WeatherApi api;

	@AfterInject
	void initService() {
		api = retrofit.create(WeatherApi.class);
	}

	@Override
	public void getWeatherForCities(List<City> cities, final RequestCallback<List<CityWeather>> callback) {
		List<String> temp = new ArrayList<>();
		for (City city : cities) {
			if (city != null) {
				temp.add(String.valueOf(city.getId()));
			}
		}

		api.getWeather(StringUtils.join(temp, ",")).enqueue(new Callback<CitiesWeather>() {
			@Override
			public void onResponse(Call<CitiesWeather> call, Response<CitiesWeather> response) {
				if (null != response && response.body() != null) {
					callback.onSuccess(response.body().getList());
				} else {
					callback.onSuccess(new ArrayList<>());
				}
			}

			@Override
			public void onFailure(Call<CitiesWeather> call, Throwable t) {
				callback.onError(t);
			}
		});
	}
}
