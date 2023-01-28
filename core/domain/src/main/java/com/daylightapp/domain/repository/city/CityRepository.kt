package com.daylightapp.domain.repository.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.city.City


import kotlinx.coroutines.flow.Flow

interface CityRepository {
     fun getAllCity() : Flow<NetworkResult<List<City>>>
}