package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class Main(
        @SerializedName("temp") val temp: String? = null,
        @SerializedName("pressure") val pressure: Double = 0.toDouble(),
        @SerializedName("humidity") val humidity: Double = 0.toDouble(),
        @SerializedName("temp_min") val tempMin: Double = 0.toDouble(),
        @SerializedName("temp_max") val tempMax: Double = 0.toDouble()

)