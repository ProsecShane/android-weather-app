package com.prosecshane.weatherapp.data.datasource.retrofit.location

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationGetter {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://ipinfo.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val locationApi: LocationApi =
        retrofit.create(LocationApi::class.java)

    suspend fun loadLocation(
        callback: (Float, Float) -> Unit,
    ) {
        val locationBody = locationApi.getLocation().body()
        if (locationBody != null) {
            val (lat, lon) = locationBody.loc.split(",").map { it.toFloat() }
            callback(lat, lon)
        } else {
            throw NullPointerException("Couldn't get the location")
        }
    }
}
