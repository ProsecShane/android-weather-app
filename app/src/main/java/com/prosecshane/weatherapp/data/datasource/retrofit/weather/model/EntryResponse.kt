package com.prosecshane.weatherapp.data.datasource.retrofit.weather.model

// Weather entry, but as a Response
data class EntryResponse(
    val weather: List<StatusResponse>,
    val main: TemperatureResponse,
    val dt: Long,
)
