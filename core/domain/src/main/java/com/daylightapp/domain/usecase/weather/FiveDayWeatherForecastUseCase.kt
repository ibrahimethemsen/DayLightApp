package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.fiveday.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface FiveDayWeatherForecastUseCase {
    operator fun invoke(lat : String,lon : String) : Flow<NetworkResult<WeatherResponse>>
}