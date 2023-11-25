package com.prosecshane.weatherapp.data.datasource.hardcoded

import com.prosecshane.weatherapp.data.datasource.WeatherAppDataSource
import com.prosecshane.weatherapp.data.model.Entry
import kotlin.random.Random

class HardCodedDataSource : WeatherAppDataSource {
    private var counter = 0

    private fun generateData(): List<Entry> {
        val result = mutableListOf<Entry>()
        counter += if (Random.nextBoolean()) 1 else -1
        for (elem in initialValues) {
            result.add(elem.copy(temperature = counter.toFloat()))
        }
        return result
    }

    override suspend fun loadEntries(): List<Entry> = generateData()
}
