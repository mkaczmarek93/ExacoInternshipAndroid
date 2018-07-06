package pl.exaco.internship.android.weatherdemo.service.utils

import pl.exaco.internship.android.weatherdemo.model.*
import java.util.*

object MockedWhether {

    fun mockedWhether(count: Int): CitiesWeather {
        val weathers = ArrayList<CityWeather>()
        val cityWeather = ArrayList<Weather>()
        cityWeather.add(Weather(0, "Słonecznie", "bez zachmurzeń", "04n"))
        val whether = CityWeather(
                Coord(15.0, -12.5),
                Sys(0, 0, 0.0, "PL", 1435610796, 1435650870),
                cityWeather,
                Main(),
                0,
                Wind(),
                Clouds(),
                0,
                0,
                "Mock Name"
        )
        for (i in 0 until count) {
            weathers.add(whether.copy())
        }
        return CitiesWeather(count, weathers)
    }
}
