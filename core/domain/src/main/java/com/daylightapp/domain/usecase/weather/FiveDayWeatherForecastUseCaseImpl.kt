package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.fiveday.WeatherResponse
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FiveDayWeatherForecastUseCaseImpl @Inject constructor(
    private val weatherRepository : WeatherRepository
) : FiveDayWeatherForecastUseCase {
    override fun invoke(
        lat: String,
        lon: String
    ): Flow<NetworkResult<WeatherResponse>> =
        weatherRepository.getFiveDayWeatherForecast(lat, lon)

}