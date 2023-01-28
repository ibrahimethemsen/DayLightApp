package com.daylightapp.domain.repository.weather


import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
     fun getWeather(
        lat : String,
        lon : String
    ) : Flow<NetworkResult<WeatherResponse>>
}