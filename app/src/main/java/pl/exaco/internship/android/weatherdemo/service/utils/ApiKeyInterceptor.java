package pl.exaco.internship.android.weatherdemo.service.utils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

	private static final String UNITS = "units";
	private static final String UNITS_VALUE = "metric";
	private static final String APP_ID = "APPID";
	private static final String API_KEY = "ffbf64c74874f4e29e9b1252ef6a0a90";
	private static final String LANGUAGE = "lang";
	private static final String LANGUAGE_VAL = "pl";

	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		final HttpUrl httpUrl = chain
				.request()
				.url()
				.newBuilder()
				.addQueryParameter(APP_ID, API_KEY)
				.addQueryParameter(UNITS, UNITS_VALUE)
				.addQueryParameter(LANGUAGE, LANGUAGE_VAL)
				.build();
		final Request request = chain
				.request()
				.newBuilder()
				.url(httpUrl)
				.build();
		return chain.proceed(request);
	}
}
