package com.prosecshane.weatherapp.data.datasource.retrofit.weather.model

data class ForecastEntryResponse(
    val weather: List<StatusResponse>,
    val temp: ForecastTemperatureResponse,
    val dt: Long,
)
