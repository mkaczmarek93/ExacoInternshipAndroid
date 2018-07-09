package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class CityWeather(
        @SerializedName("coord") val coord: Coord? = null,
        @SerializedName("sys") val sys: Sys? = null,
        @SerializedName("weather") val cityWeather: List<Weather>? = null,
        @SerializedName("main") val main: Main? = null,
        @SerializedName("visibility") val visibility: Int = 0,
        @SerializedName("wind") val wind: Wind? = null,
        @SerializedName("clouds") val clouds: Clouds? = null,
        @SerializedName("dt") val dt: Int = 0,
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String? = null,
        @SerializedName("dt_txt") val dtText : String? = null
)