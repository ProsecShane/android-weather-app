package com.prosecshane.weatherapp.data.datasource

import com.prosecshane.weatherapp.data.model.Entry

interface WeatherAppDataSource {
    suspend fun loadEntries(): List<Entry>
}
