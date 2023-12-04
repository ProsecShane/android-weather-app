package com.prosecshane.weatherapp.data.datasource.retrofit.weather.model

// Forecast response, list of forecast entry responses
data class ForecastResponse(
    val list: List<ForecastEntryResponse>,
)
