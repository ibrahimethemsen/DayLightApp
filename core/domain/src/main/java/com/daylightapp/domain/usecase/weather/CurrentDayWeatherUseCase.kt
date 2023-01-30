package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentDayWeatherUseCase {
    operator fun invoke(lat : String,lon : String) : Flow<NetworkResult<CurrentWeatherEntity>>
}