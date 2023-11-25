package com.prosecshane.weatherapp.data.repository

import com.prosecshane.weatherapp.data.datasource.hardcoded.HardCodedDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.Random

class WeatherAppRepository(
    private val rememberCityAndTime: (Int, Long) -> Unit,
    private val getCity: () -> Int,
    private val getTheme: () -> Int,
    private val getCelsius: () -> Int,
    private val getRefresh: () -> Long,
) {
    private val hardCodedDataSource = HardCodedDataSource()

    suspend fun loadEntries(
        chosenCity: Int,
        successful: Boolean = Random().nextBoolean(),
        onSuccessfulCallback: () -> Unit = {},
        onUnsuccessfulCallback: () -> Unit = {},
        onAnyCallback: suspend () -> Unit = {},
    ) = flow {
        val loadedEntries = withContext(Dispatchers.IO) { hardCodedDataSource.loadEntries() }
        emit(loadedEntries)
        if (successful) {
            withContext(Dispatchers.Main) {
                onSuccessfulCallback()
            }
            rememberCityAndTime(chosenCity, System.currentTimeMillis())
        } else {
            withContext(Dispatchers.Main) {
                onUnsuccessfulCallback()
            }
        }
        onAnyCallback()
    }

    suspend fun loadCity() = flow {
        val city = withContext(Dispatchers.IO) { getCity() }
        emit(city)
    }

    suspend fun loadTheme() = flow {
        val theme = withContext(Dispatchers.IO) { getTheme() }
        emit(theme)
    }

    suspend fun loadCelsius() = flow {
        val celsius = withContext(Dispatchers.IO) { getCelsius() }
        emit(celsius)
    }

    suspend fun loadRefresh() = flow {
        val refresh = withContext(Dispatchers.IO) { getRefresh() }
        emit(refresh)
    }
}
