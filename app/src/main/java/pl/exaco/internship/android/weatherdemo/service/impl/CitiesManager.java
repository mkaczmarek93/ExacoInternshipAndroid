package pl.exaco.internship.android.weatherdemo.service.impl;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import pl.exaco.internship.android.weatherdemo.R;
import pl.exaco.internship.android.weatherdemo.WeatherApplication;
import pl.exaco.internship.android.weatherdemo.db.IPrefDataStore;
import pl.exaco.internship.android.weatherdemo.db.impl.PrefDataStore;
import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.service.ICitiesManager;
import pl.exaco.internship.android.weatherdemo.service.RequestCallback;

@EBean(scope = EBean.Scope.Singleton)
public class CitiesManager extends BaseManager implements ICitiesManager {

	@Bean(value = PrefDataStore.class)
	IPrefDataStore prefDataStore;

	@App
	WeatherApplication app;

	@Override
	public List<City> getSavedCities() {
		return prefDataStore.getSavedCities();
	}

	@Override
	public void loadCities(RequestCallback<List<City>> callback) {
		AsyncTask.execute(() -> {
			try {
				final InputStream raw = app.getResources().openRawResource(R.raw.cities);
				final BufferedReader br = new BufferedReader(new InputStreamReader(raw));
				final Type type = TypeToken.getParameterized(List.class, City.class).getType();
				final List<City> cities = new Gson().fromJson(br, type);
				callback.onSuccess(cities);
			} catch (Exception e) {
				callback.onError(e);
			}
		});
	}

	@Override
	public void addCity(City city) {
		prefDataStore.addNewCity(city);
	}
}
