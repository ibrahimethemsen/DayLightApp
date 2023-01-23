package com.ibrahimethemsen.daylightapp.data.source.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.City

interface CityDataSource {
    suspend fun getAllCity() : NetworkResult<List<City>>
}