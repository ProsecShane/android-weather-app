package com.prosecshane.weatherapp.util

import kotlin.math.floor

fun formatTemperature(
    value: Float,
    celsius: Boolean = true,
): String {
    var trueValue = if (celsius) value else ((value * 9f) / 5f) + 32f
    trueValue = floor(trueValue * 10f) / 10f

    val result = StringBuilder()
    if (trueValue > 0f) result.append("+")
    result.append(
        if (trueValue == floor(trueValue)) trueValue.toInt() else trueValue
    )
    result.append("°")
    result.append(if (celsius) "C" else "F")
    return result.toString()
}
