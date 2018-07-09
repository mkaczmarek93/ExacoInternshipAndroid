package pl.exaco.internship.android.weatherdemo.ui.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.R;
import pl.exaco.internship.android.weatherdemo.databinding.ActivityCitiesBinding;
import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.model.FutureWeather;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.impl.ServiceFactory;
import pl.exaco.internship.android.weatherdemo.ui.city.CityActivity_;


@EActivity(R.layout.activity_weather)
@DataBound
@OptionsMenu(R.menu.menu_weather)
public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

	public static final String EXTRA_CITY_ID = "EXTRA_CITY_ID";

	private final static int CITY_ADD_REQUEST = 50232;
	private static final String TAG = WeatherActivity.class.getSimpleName();

	@Bean(ServiceFactory.class)
	IServiceFactory serviceFactory;

	@BindingObject
	ActivityCitiesBinding binding;

	private WeatherAdapter adapter;
	private WeatherContract.Presenter presenter;


	@AfterViews
	void viewCreated() {
		binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new WeatherAdapter(this);
		binding.recyclerView.setAdapter(adapter);
		presenter = new WeatherPresenter(this, serviceFactory);
		getData();




		adapter.setClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int pos = binding.recyclerView.indexOfChild(v);

				List<City> cityList = serviceFactory.getCitiesManager().getSavedCities();
				Integer cityId = cityList.get(pos).getId();

				Toast.makeText(WeatherActivity.this, cityList.get(pos).getName() + " " + cityId, Toast.LENGTH_SHORT).show();
				Intent futureWeatherIntent = new Intent(getBaseContext(), FutureWeatherActivity_.class);
				futureWeatherIntent.putExtra("id", cityId);
				startActivity(futureWeatherIntent);
				//presenter.getFutureWeather((int)cityId);
			}
		});
	}

	private void getData() {
		binding.progressBar.setVisibility(View.VISIBLE);
		presenter.getWeather();
	}

	@Override
	public void onSuccess(List<CityWeather> data) {
		binding.progressBar.setVisibility(View.GONE);
		if (data != null && !data.isEmpty()) {
			binding.recyclerView.setVisibility(View.VISIBLE);
			adapter.setItems(data);
		} else {
			binding.noData.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onFailure() {
		binding.progressBar.setVisibility(View.GONE);
		binding.noData.setVisibility(View.VISIBLE);
		binding.noData.setText(R.string.download_error);
	}

	@OptionsItem(R.id.add)
	void startNewActivity() {
		CityActivity_.intent(this)
				.startForResult(CITY_ADD_REQUEST);
	}

	@OnActivityResult(CITY_ADD_REQUEST)
	void addCityFinished(int result) {
		Log.d(TAG, "result");
		if (result == Activity.RESULT_OK) {
			getData();
		}
	}

}
