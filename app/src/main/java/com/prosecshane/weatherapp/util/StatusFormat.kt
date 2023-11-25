package com.prosecshane.weatherapp.util

import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.util.model.FormattedStatus

fun formatStatus(
    status: WeatherStatus,
    time: Long = 0,
): FormattedStatus {
    val result = FormattedStatus(
        statusFormatIcon[status]?: throw Exception("Error formatting weather status to Icon"),
        statusFormatString[status]?: throw Exception("Error formatting weather status to String"),
    )
    if ((status == WeatherStatus.Clear) || (status == WeatherStatus.Cloudy)) {
        val hour = (time % dayInMillis) / 3600000L
        if ((hour < 6) || (hour >= 18)) {
            return result.copy(
                icon = dayIconToNightIcon[result.icon]?: throw Exception(
                    "Error converting day icon to night icon while formatting weather status"
                )
            )
        }
    }
    return result
}
