package pl.exaco.internship.android.weatherdemo.ui.weather;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.CityWeather;

public interface FutureWeatherContract {

    interface Presenter {
        void getFutureWeather(Integer cityId);

    }

    interface View {
        void onSuccess(List<CityWeather> data);

        void onFailure();
    }


}
