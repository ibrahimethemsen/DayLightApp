package com.ibrahimethemsen.daylightapp.data.api.weather

import com.ibrahimethemsen.daylightapp.common.Constants.WEATHER_API_KEY
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun weatherList(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("appid") apiKey : String = WEATHER_API_KEY
    ) : WeatherResponse
}