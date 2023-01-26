package com.ibrahimethemsen.daylightapp.data.source.weather

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse

interface WeatherDataSource {
    suspend fun getWeather(lat : String,lon : String) : NetworkResult<WeatherResponse>
}