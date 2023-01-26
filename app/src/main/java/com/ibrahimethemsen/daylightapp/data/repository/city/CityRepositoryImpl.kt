package com.ibrahimethemsen.daylightapp.data.repository.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.data.source.city.CityDataSource
import com.ibrahimethemsen.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    //cityDataSourceImpl degil CityDataSource
    private val cityDataSource : CityDataSource
) : CityRepository {
    override suspend fun getAllCity(): NetworkResult<List<City>> =
        withContext(Dispatchers.IO){
            try {
                cityDataSource.getAllCity()
            }catch (e : Exception){
                NetworkResult.Error(e)
            }
        }
}