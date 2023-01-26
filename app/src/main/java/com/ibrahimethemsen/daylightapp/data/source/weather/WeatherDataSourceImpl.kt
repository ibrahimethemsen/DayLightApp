package com.ibrahimethemsen.daylightapp.data.source.weather

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.api.weather.WeatherApi
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherDataSource {
    override suspend fun getWeather(
        lat: String,
        lon: String
    ): NetworkResult<WeatherResponse> =
        try {
            val response = weatherApi.weatherList(lat, lon)
            NetworkResult.Success(response)
        }catch (e : Exception){
            NetworkResult.Error(e)
        }


}