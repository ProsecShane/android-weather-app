package com.prosecshane.weatherapp.util

import kotlin.math.abs

// Format time from millis to day and month and hour and minutes
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
    val month = givenMillis % 31557600000 / 2629800000 + 1
    return result + (monthsRU[month]?: throw Exception(
        "Error while formatting time: month can't be converted to Russian")
            )
}
