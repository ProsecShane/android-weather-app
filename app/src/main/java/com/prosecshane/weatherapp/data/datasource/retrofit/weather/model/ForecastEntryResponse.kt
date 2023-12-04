package com.prosecshane.weatherapp.data.datasource.retrofit.weather.model

// Weather entry, but as response (different from EntryResponse because of temp)
data class ForecastEntryResponse(
    val weather: List<StatusResponse>,
    val temp: ForecastTemperatureResponse,
    val dt: Long,
)
