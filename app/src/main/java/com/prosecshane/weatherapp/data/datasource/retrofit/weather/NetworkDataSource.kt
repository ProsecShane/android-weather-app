package com.prosecshane.weatherapp.data.datasource.retrofit.weather

import android.util.Log
import com.prosecshane.weatherapp.data.datasource.WeatherAppDataSource
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.WeatherApiConstants.ApiKey
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.WeatherApiConstants.ComingCount
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.WeatherApiConstants.ForecastCount
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.WeatherApiConstants.Units
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.EntryResponse
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.ComingResponse
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.ForecastEntryResponse
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.ForecastResponse
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.LocationModel
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkDataSource : WeatherAppDataSource {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val weatherApi: WeatherApi =
        retrofit.create(WeatherApi::class.java)

    override suspend fun loadEntries(
        locationModel: LocationModel,
        callback: (List<Entry>) -> Unit,
    ) {
        val currentWeatherBody = getCurrentWeatherBody(locationModel)
        val comingWeatherBody = getComingWeatherBody(locationModel)
        val forecastResponse = getWeatherForecastBody(locationModel)

        val result = mutableListOf<Entry>()
        result.add(
            entryResponseToEntry(currentWeatherBody, Implementation.Now)
        )
        for (elem in comingWeatherBody.list.takeLast(3))
            result.add(entryResponseToEntry(elem, Implementation.Close))
        for (elem in forecastResponse.list.takeLast(3))
            result.add(forecastEntryResponseToEntry(elem))

        Log.d("WeatherAppDebug", "$result")
        callback(result)
    }

    private suspend fun getCurrentWeatherBody(locationModel: LocationModel): EntryResponse {
        return (if (locationModel.usingCity) {
            weatherApi.getCurrentWeatherByCity(Units, ApiKey, locationModel.city).body()
        } else {
            weatherApi.getCurrentWeatherByCoordinates(
                Units, ApiKey, locationModel.lat, locationModel.lon
            ).body()
        })?: throw NullPointerException("Cannot get current weather")
    }

    private suspend fun getComingWeatherBody(locationModel: LocationModel): ComingResponse {
        return (if (locationModel.usingCity) {
            weatherApi.getComingWeatherByCity(
                Units, ComingCount, ApiKey, locationModel.city
            ).body()
        } else {
            weatherApi.getComingWeatherByCoordinates(
                Units, ComingCount, ApiKey, locationModel.lat, locationModel.lon
            ).body()
        })?: throw NullPointerException("Cannot get coming weather")
    }

    private suspend fun getWeatherForecastBody(locationModel: LocationModel): ForecastResponse {
        return (if (locationModel.usingCity) {
            weatherApi.getWeatherForecastByCity(
                Units, ForecastCount, ApiKey, locationModel.city
            ).body()
        } else {
            weatherApi.getWeatherForecastByCoordinates(
                Units, ForecastCount, ApiKey, locationModel.lat, locationModel.lon
            ).body()
        })?: throw NullPointerException("Cannot get weather forecast")
    }

    private fun entryResponseToEntry(
        entryResponse: EntryResponse,
        implementation: Implementation,
    ): Entry = Entry(
        temperature = entryResponse.main.temp.toFloat(),
        time = entryResponse.dt * 1000L,
        status = idToWeatherStatus(entryResponse.weather.first().id),
        implementation = implementation,
    )

    private fun forecastEntryResponseToEntry(
        entryResponse: ForecastEntryResponse
    ): Entry = Entry(
        temperature = entryResponse.temp.day.toFloat(),
        time = entryResponse.dt * 1000L,
        status = idToWeatherStatus(entryResponse.weather.first().id),
        implementation = Implementation.Forecast,
    )
}
