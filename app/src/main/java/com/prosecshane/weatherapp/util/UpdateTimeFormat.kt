package com.prosecshane.weatherapp.util

import java.util.Calendar
import kotlin.math.abs

fun formatUpdateTime(
    givenMillis: Long,
): String {
    val currentTime = Calendar.getInstance()
    val currentMillis = currentTime.timeInMillis

    if (abs(currentMillis - givenMillis) <= minuteInMillis)
        return "только что"
    return formatTime(givenMillis)
}
