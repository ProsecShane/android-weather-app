package com.prosecshane.weatherapp.data.repository

import android.util.Log
import com.prosecshane.weatherapp.data.cityNamesEnglish
import com.prosecshane.weatherapp.data.datasource.hardcoded.HardCodedDataSource
import com.prosecshane.weatherapp.data.datasource.retrofit.location.LocationGetter
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.NetworkDataSource
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.LocationModel
import com.prosecshane.weatherapp.data.model.Entry
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
    private val locationGetter = LocationGetter()

    private val hardCodedDataSource = HardCodedDataSource()
    private val networkDataSource = NetworkDataSource()

    suspend fun loadEntries(
        chosenCity: Int,
        onSuccessfulCallback: () -> Unit = {},
        onUnsuccessfulCallback: () -> Unit = {},
        onAnyCallback: suspend () -> Unit = {},
    ) = flow {
        var localEntries: List<Entry> = emptyList()
        withContext(Dispatchers.IO) {
            hardCodedDataSource.loadEntries(
                LocationModel(usingCity = true)
            ) { entries ->
                localEntries = entries
            }
        }
        emit(localEntries)

        var networkEntries: List<Entry> = emptyList()
        var successful = false
        withContext(Dispatchers.IO) {
            successful = try {
                val locationModel = if (chosenCity == 0) {
                    var lat = 0f
                    var lon = 0f
                    locationGetter.loadLocation { x, y ->
                        lat = x
                        lon = y
                    }
                    LocationModel(usingCity = false, lat = lat.toDouble(), lon = lon.toDouble())
                } else {
                    LocationModel(usingCity = true, city = cityNamesEnglish[chosenCity])
                }
                networkDataSource.loadEntries(locationModel) { entries ->
                    networkEntries = entries
                }
                true
            } catch (e: Exception) {
                Log.d("WeatherAppError", e.toString())
                false
            }
        }
        if (successful) {
            emit(networkEntries)
            withContext(Dispatchers.Main) { onSuccessfulCallback() }
            rememberCityAndTime(chosenCity, System.currentTimeMillis())
        } else {
            withContext(Dispatchers.Main) { onUnsuccessfulCallback() }
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
