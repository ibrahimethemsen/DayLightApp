package com.daylightapp.data.source.city

import com.daylightapp.data.dto.city.City

interface CityDataSource {
    suspend fun getAllCity() : com.daylightapp.common.NetworkResult<List<City>>
}