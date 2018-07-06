package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class Clouds(
        @SerializedName("all") val all: Double = 0.toDouble()
)
