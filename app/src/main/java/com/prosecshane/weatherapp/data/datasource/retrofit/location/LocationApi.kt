package com.prosecshane.weatherapp.data.datasource.retrofit.location

import com.prosecshane.weatherapp.data.datasource.retrofit.location.model.LocationResponse
import retrofit2.Response
import retrofit2.http.GET

// Api, that creates a GET HTTP request and returns LocationResponse
interface LocationApi {
    // GET at endpoint "json", return LocationResponse in Response form
    @GET("json")
    suspend fun getLocation(): Response<LocationResponse>
}
