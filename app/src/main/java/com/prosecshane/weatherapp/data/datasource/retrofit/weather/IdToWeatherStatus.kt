package com.prosecshane.weatherapp.data.datasource.retrofit.weather

import com.prosecshane.weatherapp.data.model.WeatherStatus

fun idToWeatherStatus(id: Int): WeatherStatus {
    return when {
        (id == 800) -> WeatherStatus.Clear
        (id == 801 || id == 802) -> WeatherStatus.Cloudy
        (id == 803 || id == 804) -> WeatherStatus.Overcast
        ((id / 100) == 3 || (id / 100) == 5) -> WeatherStatus.Raining
        ((id / 100) == 2) -> WeatherStatus.Thunder
        ((id / 100) == 6) -> WeatherStatus.Snowing
        ((id / 100) == 7) -> WeatherStatus.Mist
        else -> throw Exception("Invalid ID value to convert to WeatherStatus")
    }
}
