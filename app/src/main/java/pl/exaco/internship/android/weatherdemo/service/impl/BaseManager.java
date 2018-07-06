package pl.exaco.internship.android.weatherdemo.service.impl;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import retrofit2.Retrofit;

@EBean(scope = EBean.Scope.Singleton)
public abstract class BaseManager {

	@Bean
	RetrofitManager retrofitManager;

	Retrofit retrofit;

	@AfterInject
	void init() {
		retrofit = retrofitManager.getRetrofit();
	}
}
