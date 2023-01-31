package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FiveDayWeatherForecastUseCaseImpl @Inject constructor(
    private val weatherRepository : WeatherRepository
) : FiveDayWeatherForecastUseCase {
    override fun invoke(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<FiveDayWeatherEntity>>> =
        weatherRepository.getFiveDayThreeHoursWeatherForecast(lat, lon)

}