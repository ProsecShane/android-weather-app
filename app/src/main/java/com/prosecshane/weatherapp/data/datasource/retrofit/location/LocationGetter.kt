package com.prosecshane.weatherapp.data.datasource.retrofit.location

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Class, that allows to get the coordinates of the city the user is in
class LocationGetter {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://ipinfo.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val locationApi: LocationApi =
        retrofit.create(LocationApi::class.java)

    // Load location and use the resulting lat and lon
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
