package com.ibrahimethemsen.daylightapp.domain.repository.weather

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
     fun getWeather(
        lat : String,
        lon : String
    ) : Flow<NetworkResult<WeatherResponse>>
}