package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrentDayWeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : CurrentDayWeatherUseCase {
    override fun invoke(lat: String, lon: String): Flow<NetworkResult<CurrentWeatherEntity>> =
        weatherRepository.getCurrentWeather(lat, lon)
}