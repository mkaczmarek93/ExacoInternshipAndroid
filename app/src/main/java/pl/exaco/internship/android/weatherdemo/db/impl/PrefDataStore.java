package pl.exaco.internship.android.weatherdemo.db.impl;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.exaco.internship.android.weatherdemo.WeatherApplication;
import pl.exaco.internship.android.weatherdemo.db.IPrefDataStore;
import pl.exaco.internship.android.weatherdemo.model.City;

@EBean(scope = EBean.Scope.Singleton)
public class PrefDataStore implements IPrefDataStore {

	private static final String CITIES = "CITIES";
	private static final int MAX_CITIES = 5;
	private static final int FIRST_ITEM = 0;

	@App
	WeatherApplication application;

	private List<City> cities;

	@Override
	public List<City> getSavedCities() {
		if (cities == null || cities.isEmpty()) {
			cities = getList(CITIES, City.class);
		}
		return cities;
	}

	@Override
	public void addNewCity(City city) {
		if (cities == null) {
			cities = new ArrayList<>();
		}
		if (cities.size() == MAX_CITIES) {
			cities.remove(FIRST_ITEM);
		}
		cities.add(city);
		setList(cities, CITIES);
	}

	private <T> void setList(List<T> list, String prefKey) {
		final String listString = new Gson().toJson(list);
		getPreferences().edit().putString(prefKey, listString).apply();
	}

	private <T> List<T> getList(String prefKey, Class<T> listObjectClass) {
		final String listString = getPreferences().getString(prefKey, null);
		if (listString == null) {
			return new ArrayList<>();
		}
		Type listType = TypeToken.getParameterized(List.class, listObjectClass).getType();
		return new Gson().fromJson(listString, listType);
	}

	private SharedPreferences getPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(application);
	}
}
