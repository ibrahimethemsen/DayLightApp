package com.ibrahimethemsen.daylightapp.data.source.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.api.CityApi
import com.ibrahimethemsen.daylightapp.data.dto.City
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