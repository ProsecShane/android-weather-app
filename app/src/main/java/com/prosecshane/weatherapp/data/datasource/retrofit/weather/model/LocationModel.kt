package com.prosecshane.weatherapp.data.datasource.retrofit.weather.model

data class LocationModel(
    val city: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val usingCity: Boolean,
)
