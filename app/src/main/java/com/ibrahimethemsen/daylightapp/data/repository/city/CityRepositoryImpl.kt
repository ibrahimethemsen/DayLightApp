package com.ibrahimethemsen.daylightapp.data.repository.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.data.source.city.CityDataSource
import com.ibrahimethemsen.daylightapp.di.IoDispatcher
import com.ibrahimethemsen.daylightapp.domain.repository.city.CityRepository
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