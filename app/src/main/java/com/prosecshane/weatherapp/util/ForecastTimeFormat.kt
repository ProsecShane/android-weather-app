package com.prosecshane.weatherapp.util

fun formatForecastTime(
    givenMillis: Long,
): String {
    val result = formatTime(givenMillis).split(" ")
    return result.takeLast(result.size - 1).joinToString(" ")
}
