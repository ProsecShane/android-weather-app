package com.prosecshane.weatherapp.data.sharedprefs

import android.content.Context
import androidx.core.content.edit

// Api, allows easier use of SP
class SpApi(context: Context) {
    private val prefs = context.getSharedPreferences("WeatherAppSP", Context.MODE_PRIVATE)

    // Set Any, the Api determines the type by itself
    fun set(key: String, value: Any) {
        prefs.edit(commit = true) {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                else -> throw Exception(
                    "Type ${value::class.java.simpleName} not supported for SpApi"
                )
            }
        }
    }

    // Get Any, the type is determined by the fallback value
    fun <T> get(key: String, fallback: T): T {
        val result = when (fallback) {
            is Int -> prefs.getInt(key, fallback)
            is Long -> prefs.getLong(key, fallback)
            is Float -> prefs.getFloat(key, fallback)
            is String -> prefs.getString(key, fallback)
            is Boolean -> prefs.getBoolean(key, fallback)
            else -> throw Exception(
                "Type ${fallback!!::class.java.simpleName} not supported for SpApi"
            )
        }
        return result as T
    }
}
