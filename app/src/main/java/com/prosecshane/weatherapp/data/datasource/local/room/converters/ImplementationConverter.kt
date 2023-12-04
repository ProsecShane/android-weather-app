package com.prosecshane.weatherapp.data.datasource.local.room.converters

import androidx.room.TypeConverter
import com.prosecshane.weatherapp.data.model.Implementation

// Converter from Implementation (local enum) to Int and back for Room
class ImplementationConverter {
    // Converts from Implementation to Int
    @TypeConverter
    fun implToInt(i: Implementation): Int = when (i) {
        Implementation.Now -> 0
        Implementation.Close -> 1
        Implementation.Forecast -> 2
    }

    // Converts from Int to Implementation
    @TypeConverter
    fun intToImpl(i: Int): Implementation = when (i) {
        0 -> Implementation.Now
        1 -> Implementation.Close
        2 -> Implementation.Forecast
        else -> throw Exception("Local Database - ImplementationConverter Error")
    }
}
