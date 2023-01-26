package com.ibrahimethemsen.daylightapp.domain.repository.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City

interface CityRepository {
    suspend fun getAllCity() : NetworkResult<List<City>>
}