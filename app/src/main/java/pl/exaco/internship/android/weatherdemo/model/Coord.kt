package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class Coord(
        @SerializedName("lon") val lon: Double = 0.toDouble(),
        @SerializedName("lat") val lat: Double = 0.toDouble()
)