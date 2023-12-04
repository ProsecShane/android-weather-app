package com.prosecshane.weatherapp.data.datasource.local.room.converters

import androidx.room.TypeConverter
import com.prosecshane.weatherapp.data.model.WeatherStatus

// Converter from WeatherStatus (local enum) to Int and back for Room
class StatusConverter {
    // Converts from WeatherStatus to Int
    @TypeConverter
    fun statusToInt(s: WeatherStatus): Int = when (s) {
        WeatherStatus.Clear -> 0
        WeatherStatus.Cloudy -> 1
        WeatherStatus.Overcast -> 2
        WeatherStatus.Raining -> 3
        WeatherStatus.Thunder -> 4
        WeatherStatus.Snowing -> 5
        WeatherStatus.Mist -> 6
    }

    // Converts from Int to WeatherStatus
    @TypeConverter
    fun intToStatus(i: Int): WeatherStatus = when (i) {
        0 -> WeatherStatus.Clear
        1 -> WeatherStatus.Cloudy
        2 -> WeatherStatus.Overcast
        3 -> WeatherStatus.Raining
        4 -> WeatherStatus.Thunder
        5 -> WeatherStatus.Snowing
        6 -> WeatherStatus.Mist
        else -> throw Exception("Local Database - StatusConverter Error")
    }
}
