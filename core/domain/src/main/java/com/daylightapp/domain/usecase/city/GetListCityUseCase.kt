package com.daylightapp.domain.usecase.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.city.LocationEntity
import kotlinx.coroutines.flow.Flow

interface GetListCityUseCase {
    operator  fun invoke() : Flow<NetworkResult<List<LocationEntity>>>
}