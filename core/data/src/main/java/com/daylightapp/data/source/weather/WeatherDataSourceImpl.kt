package com.daylightapp.data.source.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.dto.weather.current.CurrentWeather
import com.daylightapp.data.api.weather.WeatherApi
import com.daylightapp.data.dto.weather.fiveday.WeatherResponse
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherDataSource {
    override suspend fun getFiveDayWeatherForecast(
        lat: String,
        lon: String
    ): NetworkResult<WeatherResponse> =
        try {
            val response = weatherApi.fiveDayWeatherForecast(lat, lon)
            NetworkResult.Success(response)
        }catch (e : Exception){
            NetworkResult.Error(e)
        }

    override suspend fun getCurrentDayWeather(
        lat: String,
        lon: String
    ): NetworkResult<CurrentWeather> =
        try {
            val response = weatherApi.currentDayWeather(lat, lon)
            NetworkResult.Success(response)
        }catch (e : Exception){
            NetworkResult.Error(e)
        }

}