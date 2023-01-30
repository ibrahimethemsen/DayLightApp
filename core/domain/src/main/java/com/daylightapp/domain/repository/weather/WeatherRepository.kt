package com.daylightapp.domain.repository.weather


import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.fiveday.WeatherResponse
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getFiveDayWeatherForecast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<WeatherResponse>>

    fun getCurrentWeather(
        lat : String,
        lon : String
    ) : Flow<NetworkResult<CurrentWeatherEntity>>

}