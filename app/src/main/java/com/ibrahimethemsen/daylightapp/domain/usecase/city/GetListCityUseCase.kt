package com.ibrahimethemsen.daylightapp.domain.usecase.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import kotlinx.coroutines.flow.Flow

interface GetListCityUseCase {
    operator fun invoke() : Flow<NetworkResult<List<City>>>
}