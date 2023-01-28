package com.daylightapp.data.source.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.WeatherResponse

interface WeatherDataSource {
    suspend fun getWeather(lat : String,lon : String) : com.daylightapp.common.NetworkResult<WeatherResponse>
}