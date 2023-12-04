package com.prosecshane.weatherapp.util

import java.util.Calendar

// Format update time, uses "ago" format
fun formatUpdateTime(
    givenMillis: Long,
): String {
    val currentTime = Calendar.getInstance()
    val currentMillis = currentTime.timeInMillis
    val difference = currentMillis - givenMillis

    return when {
        difference < minuteInMillis -> "только что"
        difference < hourInMillis -> "${difference / minuteInMillis} мин. назад"
        difference < dayInMillis -> "${difference / hourInMillis} ч. назад"
        else -> formatForecastTime(givenMillis)
    }
}
