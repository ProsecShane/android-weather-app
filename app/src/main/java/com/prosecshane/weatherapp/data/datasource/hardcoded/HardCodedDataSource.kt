package com.prosecshane.weatherapp.data.datasource.hardcoded

import com.prosecshane.weatherapp.data.datasource.WeatherAppDataSource
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.LocationModel
import com.prosecshane.weatherapp.data.model.Entry
import kotlin.random.Random

// Hard coded data source, on every update increases or decreases all temperature values by one
class HardCodedDataSource : WeatherAppDataSource {
    private var counter = 0

    // Create new values
    private fun generateData(): List<Entry> {
        val result = mutableListOf<Entry>()
        counter += if (Random.nextBoolean()) 1 else -1
        for (elem in initialValues) {
            result.add(elem.copy(temperature = counter.toFloat()))
        }
        return result
    }

    // Give generated values
    override suspend fun loadEntries(
        locationModel: LocationModel,
        callback: (List<Entry>) -> Unit,
    ) {
        callback(generateData())
    }
}
