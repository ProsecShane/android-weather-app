package com.prosecshane.weatherapp.util

import kotlin.math.floor

fun formatTemperature(
    value: Float,
    celsius: Boolean = true,
): String {
    val actualValue = if (celsius) value else ((value * 9f) / 5f) + 32f
    val trueValue = if (actualValue - floor(actualValue) < 0.5f)
        floor(actualValue).toInt()
    else floor(actualValue).toInt() + 1

    val result = StringBuilder()
    if (trueValue > 0f) result.append("+")
    result.append(trueValue)
    result.append("°")
    result.append(if (celsius) "C" else "F")
    return result.toString()
}
