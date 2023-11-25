package com.prosecshane.weatherapp.data.model

data class Entry(
    val temperature: Float,
    val time: Long,
    val status: WeatherStatus,
    val implementation: Implementation,
)
