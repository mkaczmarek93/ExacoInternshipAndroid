package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class Sys(
        @SerializedName("type") val type: Int = 0,
        @SerializedName("id") val id: Int = 0,
        @SerializedName("message") val message: Double = 0.toDouble(),
        @SerializedName("country") val country: String? = null,
        @SerializedName("sunrise") val sunrise: Long = 0,
        @SerializedName("sunset") val sunset: Long = 0
)