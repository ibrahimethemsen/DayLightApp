package com.daylightapp.data.source.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.city.City

interface CityDataSource {
    suspend fun getAllCity() : com.daylightapp.common.NetworkResult<List<City>>
}