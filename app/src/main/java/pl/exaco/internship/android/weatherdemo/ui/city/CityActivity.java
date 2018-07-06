package pl.exaco.internship.android.weatherdemo.ui.city;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.R;
import pl.exaco.internship.android.weatherdemo.databinding.ActivityFindCityBinding;
import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.impl.ServiceFactory;

@DataBound
@EActivity(R.layout.activity_city)
public class CityActivity extends AppCompatActivity implements CityContract.View {

	@BindingObject
	ActivityFindCityBinding binding;

	@Bean(ServiceFactory.class)
	IServiceFactory serviceFactory;

	CityContract.Presenter presenter;
	CityAdapter adapter;

	@AfterViews
	void onViewCreated() {
		presenter = new CityPresenter(this, serviceFactory);
		adapter = new CityAdapter(this, city -> {
			presenter.addCity(city);
			setResult(Activity.RESULT_OK);
			finish();
		});
		binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
		binding.recyclerView.setHasFixedSize(false);
		binding.recyclerView.setAdapter(adapter);
		binding.search.onActionViewExpanded();
		binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				adapter.getFilter().filter(newText, count -> filterResult(count));
				return true;
			}
		});
		loadData();
	}

	private void filterResult(int count) {
		binding.setHasItems(count > 0);
		if (count > 0) {
			binding.noData.setVisibility(View.GONE);
		} else {
			binding.noData.setVisibility(View.VISIBLE);
		}
	}

	private void loadData() {
		binding.progressBar.setVisibility(View.VISIBLE);
		binding.recyclerView.setVisibility(View.GONE);
		binding.search.setVisibility(View.GONE);
		binding.noData.setVisibility(View.GONE);
		presenter.loadCities();
	}

	@UiThread
	@Override
	public void onCitiesLoaded(List<City> cities) {
		binding.progressBar.setVisibility(View.GONE);
		binding.search.setVisibility(View.VISIBLE);
		if (cities.isEmpty()) {
			binding.noData.setVisibility(View.VISIBLE);
		}
		binding.setHasItems(!cities.isEmpty());
		adapter.setItems(cities);
	}
}
