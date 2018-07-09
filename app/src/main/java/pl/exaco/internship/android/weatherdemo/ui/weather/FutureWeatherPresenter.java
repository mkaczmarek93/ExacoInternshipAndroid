package pl.exaco.internship.android.weatherdemo.ui.weather;

import android.util.Log;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.model.FutureWeather;
import pl.exaco.internship.android.weatherdemo.service.IFutureWeatherManager;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.RequestCallback;

public class FutureWeatherPresenter implements FutureWeatherContract.Presenter {

    private static final String TAG = FutureWeatherPresenter.class.getSimpleName();

    private final FutureWeatherContract.View view;
    private final IFutureWeatherManager futureWeatherManager;

    public FutureWeatherPresenter(FutureWeatherContract.View view, IServiceFactory serviceFactory) {
        this.view = view;
        this.futureWeatherManager = serviceFactory.getFutureWeatherManager();
    }


    @Override
    public void getFutureWeather(Integer cityId) {
        futureWeatherManager.getFutureWeatherByCity(cityId, new RequestCallback<List<CityWeather>>() {
            @Override
            public void onSuccess(List<CityWeather> data) {
                view.onSuccess(data);
                Log.e(TAG, "Success future weather");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "Future weather failure");
            }
        });
    }
}
