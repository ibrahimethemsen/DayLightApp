package com.ibrahimethemsen.daylightapp.domain.repository.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
     fun getAllCity() : Flow<NetworkResult<List<City>>>
}