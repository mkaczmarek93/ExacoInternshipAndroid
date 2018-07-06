package pl.exaco.internship.android.weatherdemo.ui.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomDataBinding {

    @BindingAdapter(value = "imageUrl")
    public static void setImageUrl(ImageView image, String iconName) {
        final String url = "http://openweathermap.org/img/w/" + iconName + ".png";
        Glide.with(image.getContext())
                .load(url)
                .into(image);
    }

    @BindingAdapter(value = "dateTime")
    public static void setDateTime(TextView textView, Long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        long date = System.currentTimeMillis();
        if (time != null) {
            date = time * 1000;
        }
        textView.setText(formatter.format(new Date(date)));
    }

    @BindingAdapter(value = "doubleText")
    public static void setDouble(TextView textView, Double value) {
        final DecimalFormat df = new DecimalFormat("#.00");
        textView.setText(df.format(value));
    }
}
