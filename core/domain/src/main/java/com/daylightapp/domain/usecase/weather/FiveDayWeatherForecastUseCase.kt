package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import kotlinx.coroutines.flow.Flow

interface FiveDayWeatherForecastUseCase {
    operator fun invoke(lat : String,lon : String) : Flow<NetworkResult<List<FiveDayWeatherEntity>>>
}