package pl.exaco.internship.android.weatherdemo.service.impl;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.exaco.internship.android.weatherdemo.service.IRetrofitManager;
import pl.exaco.internship.android.weatherdemo.service.utils.ApiKeyInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean(scope = EBean.Scope.Singleton)
public class RetrofitManager implements IRetrofitManager {

	private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/";

	private Retrofit retrofitClient;

	@AfterInject
	void init() {

		final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(loggingInterceptor)
				.addInterceptor(new ApiKeyInterceptor())
				.build();

		retrofitClient = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(BASE_URL)
				.client(client)
				.build();
	}

	@Override
	public Retrofit getRetrofit() {
		return retrofitClient;
	}

}