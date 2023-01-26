package com.ibrahimethemsen.daylightapp.domain.usecase.weather

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface FiveDayWeatherForecastUseCase {
    operator fun invoke(lat : String,lon : String) : Flow<NetworkResult<WeatherResponse>>
}