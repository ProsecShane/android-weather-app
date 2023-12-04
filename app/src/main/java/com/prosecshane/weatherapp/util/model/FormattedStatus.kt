package com.prosecshane.weatherapp.util.model

// Model that is the result of converting from WeatherStatus
// Contains icon ID and string
data class FormattedStatus(
    val icon: Int,
    val text: String,
)
