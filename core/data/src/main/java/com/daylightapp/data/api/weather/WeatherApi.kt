package com.daylightapp.data.api.weather


import com.daylightapp.data.dto.weather.current.CurrentWeather
import com.daylightapp.data.common.Constants.WEATHER_API_KEY
import com.daylightapp.data.dto.weather.fiveday.WeatherResponse
import com.daylightapp.data.common.Constants.API_LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun fiveDayWeatherForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("lang") lang : String = API_LANGUAGE,
        @Query("appid") apiKey : String = WEATHER_API_KEY
    ) : WeatherResponse

    @GET("data/2.5/weather")
    suspend fun currentDayWeather(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("lang") lang : String = API_LANGUAGE,
        @Query("appid") apiKey : String = WEATHER_API_KEY
    ) : CurrentWeather
}