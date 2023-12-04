package com.prosecshane.weatherapp.util

// Format millis to only date (day and month), used for forecasts
fun formatForecastTime(
    givenMillis: Long,
): String {
    val result = formatTime(givenMillis).split(" ")
    return result.takeLast(result.size - 1).joinToString(" ")
}
