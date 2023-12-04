package com.prosecshane.weatherapp.data.datasource.retrofit.weather

import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.ComingResponse
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.EntryResponse
import com.prosecshane.weatherapp.data.datasource.retrofit.weather.model.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Api, gives weather entries by city or coordinates
interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeatherByCity(
        @Query("units") units: String,
        @Query("appid") apiKey: String,
        @Query("q") cityName: String,
    ): Response<EntryResponse>

    @GET("weather")
    suspend fun getCurrentWeatherByCoordinates(
        @Query("units") units: String,
        @Query("appid") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): Response<EntryResponse>

    @GET("forecast")
    suspend fun getComingWeatherByCity(
        @Query("units") units: String,
        @Query("cnt") count: Int,
        @Query("appid") apiKey: String,
        @Query("q") cityName: String,
    ): Response<ComingResponse>

    @GET("forecast")
    suspend fun getComingWeatherByCoordinates(
        @Query("units") units: String,
        @Query("cnt") count: Int,
        @Query("appid") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): Response<ComingResponse>

    @GET("forecast/daily")
    suspend fun getWeatherForecastByCity(
        @Query("units") units: String,
        @Query("cnt") count: Int,
        @Query("appid") apiKey: String,
        @Query("q") cityName: String,
    ): Response<ForecastResponse>

    @GET("forecast/daily")
    suspend fun getWeatherForecastByCoordinates(
        @Query("units") units: String,
        @Query("cnt") count: Int,
        @Query("appid") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): Response<ForecastResponse>
}
