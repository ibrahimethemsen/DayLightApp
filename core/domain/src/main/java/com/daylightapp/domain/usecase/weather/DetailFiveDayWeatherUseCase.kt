package com.daylightapp.domain.usecase.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import kotlinx.coroutines.flow.Flow

interface DetailFiveDayWeatherUseCase {
    operator fun invoke(lat : String,lon : String) : Flow<NetworkResult<List<DetailFiveDayWeatherEntity>>>
}