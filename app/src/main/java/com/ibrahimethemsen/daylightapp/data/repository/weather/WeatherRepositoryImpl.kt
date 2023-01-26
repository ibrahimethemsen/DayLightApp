package com.ibrahimethemsen.daylightapp.data.repository.weather

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse
import com.ibrahimethemsen.daylightapp.data.source.weather.WeatherDataSource
import com.ibrahimethemsen.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override fun getWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<WeatherResponse>> {
        return flow {
                emit(NetworkResult.Loading)
                when(val response = weatherDataSource.getWeather(lat, lon)){
                    is NetworkResult.Error -> {
                        emit(NetworkResult.Error(response.exception))
                    }
                    NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {

                            emit(NetworkResult.Success(response.data))

                    }
                }
        }
    }


}