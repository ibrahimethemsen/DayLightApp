package com.ibrahimethemsen.daylightapp.domain.usecase.weather

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse
import com.ibrahimethemsen.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FiveDayWeatherForecastUseCaseImpl @Inject constructor(
    private val weatherRepository : WeatherRepository
) : FiveDayWeatherForecastUseCase {
    override fun invoke(
        lat: String,
        lon: String
    ): Flow<NetworkResult<WeatherResponse>> =
        weatherRepository.getWeather(lat, lon)

}