package pl.exaco.internship.android.weatherdemo.ui.weather;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.exaco.internship.android.weatherdemo.R;
import pl.exaco.internship.android.weatherdemo.databinding.ItemFutureWeatherBinding;
import pl.exaco.internship.android.weatherdemo.databinding.ItemWeatherBinding;
import pl.exaco.internship.android.weatherdemo.model.CityWeather;


public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.FutureWeatherViewHolder> {

    private final List<CityWeather> items = new ArrayList<>();
    private final Context context;

    public FutureWeatherAdapter(Context context) {
        this.context = context;
    }

    void setItems(@NonNull List<CityWeather> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override

    public FutureWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_future_weather, parent, false);
        return new FutureWeatherViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(FutureWeatherViewHolder holder, int position) {
        if (getItemCount() > position) {
            final CityWeather weather = items.get(position);
            holder.binData(weather);
        }

    }

    class FutureWeatherViewHolder extends RecyclerView.ViewHolder {

        ItemFutureWeatherBinding binding;

        public FutureWeatherViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        void binData(CityWeather data) {
            binding.setCityWeather(data);
            binding.setDescription(data.getCityWeather().get(0));
        }
    }
}
