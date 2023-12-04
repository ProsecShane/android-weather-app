package com.prosecshane.weatherapp.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prosecshane.weatherapp.data.datasource.local.LocalDataSourceConstants.CACHE_TABLE_VERSION
import com.prosecshane.weatherapp.data.datasource.local.room.converters.ImplementationConverter
import com.prosecshane.weatherapp.data.datasource.local.room.converters.StatusConverter
import com.prosecshane.weatherapp.data.model.Entry

// Database object, gives Dao
@Database(
    version = CACHE_TABLE_VERSION,
    entities = [
        Entry::class,
    ]
)
@TypeConverters(
    ImplementationConverter::class,
    StatusConverter::class,
)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun entriesDao(): WeatherAppDao
}
