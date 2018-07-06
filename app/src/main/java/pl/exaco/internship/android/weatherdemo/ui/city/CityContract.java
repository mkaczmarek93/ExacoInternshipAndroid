package pl.exaco.internship.android.weatherdemo.ui.city;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.City;

public interface CityContract {

	interface View {
		void onCitiesLoaded(List<City> cities);
	}

	interface Presenter {
		void loadCities();

		void addCity(City city);
	}
}
