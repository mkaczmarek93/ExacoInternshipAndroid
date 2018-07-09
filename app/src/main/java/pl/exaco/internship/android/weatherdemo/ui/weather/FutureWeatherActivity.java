package pl.exaco.internship.android.weatherdemo.ui.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;

import java.util.List;

import pl.exaco.internship.android.weatherdemo.R;
import pl.exaco.internship.android.weatherdemo.databinding.ActivityCitiesBinding;
import pl.exaco.internship.android.weatherdemo.databinding.ActivityFutureWeatherBinding;
import pl.exaco.internship.android.weatherdemo.model.City;
import pl.exaco.internship.android.weatherdemo.model.CityWeather;
import pl.exaco.internship.android.weatherdemo.service.IServiceFactory;
import pl.exaco.internship.android.weatherdemo.service.impl.ServiceFactory;


@EActivity(R.layout.content_future_weather)
@DataBound
public class FutureWeatherActivity extends AppCompatActivity implements FutureWeatherContract.View{


    private static final String TAG = FutureWeatherActivity.class.getSimpleName();

    @Bean(ServiceFactory.class)
    IServiceFactory serviceFactory;

    @BindingObject
    ActivityFutureWeatherBinding binding;

    private FutureWeatherAdapter adapter;
    private FutureWeatherContract.Presenter presenter;

    @AfterViews
    void viewCreated() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FutureWeatherAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        presenter = new FutureWeatherPresenter(this, serviceFactory);
        Log.e(TAG, "view created");
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id", -1);
        Log.e(TAG, "id = " + id);
        getData(id);
    }

    private void getData(Integer id) {
        Log.e(TAG, "JEJEJEJEJEJEJJEJEJEJJE");
        presenter.getFutureWeather(id);
    }

    @Override
    public void onSuccess(List<CityWeather> data) {
        if (data != null && !data.isEmpty()) {
            binding.recyclerView.setVisibility(View.VISIBLE);
            adapter.setItems(data);
            Log.e(TAG, "Successs");
        } else {
            Log.e(TAG, "activity failure");
        }
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "activity failure x2");
    }

}
