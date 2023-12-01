package com.prosecshane.weatherapp.data.datasource.retrofit.weather.model

data class EntryResponse(
    val weather: List<StatusResponse>,
    val main: TemperatureResponse,
    val dt: Long,
)
