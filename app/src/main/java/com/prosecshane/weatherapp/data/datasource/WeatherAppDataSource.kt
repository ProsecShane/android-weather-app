package com.prosecshane.weatherapp.data.datasource

import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.LocationModel
import com.prosecshane.weatherapp.data.model.Entry

// Interface, defines what a data source in this app should do
interface WeatherAppDataSource {
    suspend fun loadEntries(
        locationModel: LocationModel,
        callback: (List<Entry>) -> Unit,
    )
}
