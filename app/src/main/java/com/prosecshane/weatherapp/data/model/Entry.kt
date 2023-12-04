package com.prosecshane.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prosecshane.weatherapp.data.datasource.local.LocalDataSourceConstants.CACHE_TABLE_NAME
import java.util.UUID

// Main model for storing data in app
@Entity(tableName = CACHE_TABLE_NAME)
data class Entry(
    @ColumnInfo(name = "temperature")
    val temperature: Float,

    @ColumnInfo(name = "time")
    val time: Long,

    @ColumnInfo(name = "status")
    val status: WeatherStatus,

    @ColumnInfo(name = "implementation")
    val implementation: Implementation,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
)
