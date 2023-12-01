package com.prosecshane.weatherapp.data.datasource.retrofit.location

import com.prosecshane.weatherapp.data.datasource.retrofit.location.model.LocationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface LocationApi {
    @GET("json")
    suspend fun getLocation(): Response<LocationResponse>
}
