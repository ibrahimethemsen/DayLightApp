package com.daylightapp.data.repository.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.WeatherResponse
import com.daylightapp.data.source.weather.WeatherDataSource
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override fun getWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<WeatherResponse>> = flow<NetworkResult<WeatherResponse>> {
        emit(NetworkResult.Loading)
    }.catch {
        emit(NetworkResult.Error(it))
    }.map {
        weatherDataSource.getWeather(lat, lon)
    }


}