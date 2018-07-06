package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class FutureWeather(
        @SerializedName("cod") val cod: String,
        @SerializedName("message") val message: Double,
        @SerializedName("cnt") val cnt: Int,
        @SerializedName("list") val list: List<CityWeather>
)
