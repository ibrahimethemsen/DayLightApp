package com.daylightapp.data.repository.city



import com.daylightapp.common.NetworkResult

import com.daylightapp.data.di.IoDispatcher
import com.daylightapp.common.city.City
import com.daylightapp.data.source.city.CityDataSource
import com.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    //cityDataSourceImpl degil CityDataSource
    private val cityDataSource: CityDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CityRepository {
    override fun getAllCity(): Flow<NetworkResult<List<City>>> =
        flow<NetworkResult<List<City>>> {
            emit(NetworkResult.Loading)
        }.catch {
            emit(NetworkResult.Error(it))
        }.map {
            cityDataSource.getAllCity()
        }
}