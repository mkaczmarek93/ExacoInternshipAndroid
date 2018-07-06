package pl.exaco.internship.android.weatherdemo.ui.city;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import pl.exaco.internship.android.weatherdemo.R;
import pl.exaco.internship.android.weatherdemo.databinding.ItemSimpleCityBinding;
import pl.exaco.internship.android.weatherdemo.model.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.SimpleCityViewHolder> implements Filterable {

	private final Context context;
	private final AdapterListener listener;

	private final CityFilter filter = new CityFilter();
	private final List<City> items = new ArrayList<>();
	private final List<City> filtered = new ArrayList<>();

	CityAdapter(Context context, AdapterListener listener) {
		this.context = context;
		this.listener = listener;
	}

	void setItems(@NonNull List<City> items) {
		this.items.clear();
		this.items.addAll(items);
		this.filtered.clear();
		this.filtered.addAll(items);
		this.notifyDataSetChanged();
	}

	private void setFilteredResult(List<City> items) {
		filtered.clear();
		filtered.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return filtered.size();
	}

	@Override
	public SimpleCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View itemView = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
		return new SimpleCityViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SimpleCityViewHolder holder, int position) {
		if (position < getItemCount()) {
			final City city = filtered.get(position);
			holder.bindData(city);
			holder.binding.getRoot().setOnClickListener(v -> listener.onCitySelected(city));
		}
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	//TODO implement filter
	class CityFilter extends android.widget.Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			final List<City> cities = new ArrayList<>();
			for (City city : items) {
				if (city != null && city.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
					cities.add(city);
				}
			}
			results.values = cities;
			results.count = cities.size();
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			setFilteredResult((List<City>) results.values);
		}
	}

	static class SimpleCityViewHolder extends RecyclerView.ViewHolder {


		private final ItemSimpleCityBinding binding;

		public SimpleCityViewHolder(View itemView) {
			super(itemView);
			binding = DataBindingUtil.bind(itemView);
		}

		void bindData(City data) {
			binding.setCity(data);
		}
	}

	public interface AdapterListener {
		void onCitySelected(City city);
	}
}