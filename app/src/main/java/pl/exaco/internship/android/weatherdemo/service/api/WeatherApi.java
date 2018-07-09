package pl.exaco.internship.android.weatherdemo.service.api;

import pl.exaco.internship.android.weatherdemo.model.CitiesWeather;
import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.model.FutureWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


	/**
	 * URL = "group"
	 * query param - id - example: 1,2,3,4
	 * <p>
	 * return - CitiesWeather
	 */

	@GET("group")
	Call<CitiesWeather> getWeather(@Query("id") String ids);

	@GET("forecast")
	Call<FutureWeather> getFutureWeather(@Query("id") String id);


}
