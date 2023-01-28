package com.daylightapp.data.source.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.api.city.CityApi
import com.daylightapp.common.city.City
import javax.inject.Inject

class CityDataSourceImpl @Inject constructor(
    private val cityApi: CityApi
) : CityDataSource {
    override suspend fun getAllCity(): NetworkResult<List<City>> =
        try {
            val response = cityApi.getAllCity()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }

}