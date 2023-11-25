package com.prosecshane.weatherapp.util

import java.util.Calendar
import kotlin.math.abs

fun formatTime(
    givenMillis: Long,
): String {
    val currentMillis = System.currentTimeMillis()
    val currentDay = currentMillis / dayInMillis

    val givenDay = givenMillis / dayInMillis

    if ((currentDay == givenDay) ||
        (abs(currentMillis - givenMillis) <= tenHoursInMillis)) {
        return simpleFormatter.format(givenMillis)
    }
    val result = fullFormatter.format(givenMillis)
    val month = givenMillis % 31536000000 / 2592000025 + 1
    return result + (monthsRU[month]?: throw Exception(
        "Error while formatting time: month can't be converted to Russian")
            )
}
