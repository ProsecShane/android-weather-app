package com.prosecshane.weatherapp.data.datasource.local

import com.prosecshane.weatherapp.data.datasource.WeatherAppDataSource
import com.prosecshane.weatherapp.data.datasource.local.room.WeatherAppDatabase
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.LocationModel
import com.prosecshane.weatherapp.data.model.Entry

// Local data source, uses Room for storing information in case of no connection
class LocalDataSource(
    databaseMaker: () -> WeatherAppDatabase,
) : WeatherAppDataSource {
    private val database = databaseMaker()

    // Load entries using Dao from Database
    override suspend fun loadEntries(
        locationModel: LocationModel,
        callback: (List<Entry>) -> Unit
    ) {
        callback(database.entriesDao().getEntries())
    }

    // Clear table and add new values to it
    suspend fun saveNewEntries(entries: List<Entry>) {
        database.entriesDao().clearEntries()
        for (entry in entries)
            database.entriesDao().addEntries(entry)
    }
}
