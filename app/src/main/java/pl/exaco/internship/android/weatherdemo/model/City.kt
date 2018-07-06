package pl.exaco.internship.android.weatherdemo.model

import com.google.gson.annotations.SerializedName

data class City(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String? = null,
        @SerializedName("country") val country: String? = null,
        @SerializedName("coord") val coord: Coord? = null
)