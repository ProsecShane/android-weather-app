package com.prosecshane.weatherapp.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prosecshane.weatherapp.data.datasource.local.LocalDataSourceConstants.CACHE_TABLE_NAME
import com.prosecshane.weatherapp.data.model.Entry

// Room Dao for working with database
@Dao
interface WeatherAppDao {
    // Add an entry to DB
    @Insert suspend fun addEntries(vararg entries: Entry)

    // Clear table
    @Query("DELETE FROM $CACHE_TABLE_NAME")
    suspend fun clearEntries()

    // Get all values from table
    @Query("SELECT * FROM $CACHE_TABLE_NAME")
    suspend fun getEntries(): List<Entry>
}
