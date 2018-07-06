package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class Wind(
        @SerializedName("speed") val speed: Double? = null,
        @SerializedName("deg") val deg: Double? = null
)