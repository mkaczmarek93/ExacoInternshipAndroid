package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class CitiesWeather(
        @SerializedName("cnt") val count: Int = 0,
        @SerializedName("list") val list: List<CityWeather>? = null,
        @SerializedName("dt_txt") val dateString: String? = null,
        @SerializedName("humidity") private val humidity: Int = 0,
        @SerializedName("pressure") private val pressure: Double = 0.0
)