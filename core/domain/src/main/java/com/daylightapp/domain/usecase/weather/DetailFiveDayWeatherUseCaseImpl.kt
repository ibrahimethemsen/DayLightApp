package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailFiveDayWeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : DetailFiveDayWeatherUseCase {
    override fun invoke(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<DetailFiveDayWeatherEntity>>> =
        weatherRepository.getFiveDayDetailWeatherForeCast(lat, lon)
}