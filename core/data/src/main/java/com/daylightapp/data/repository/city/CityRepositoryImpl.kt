package com.daylightapp.data.repository.city


import com.daylightapp.common.NetworkResult
import com.daylightapp.data.di.IoDispatcher
import com.daylightapp.data.dto.city.City
import com.daylightapp.data.mapper.ListMapper
import com.daylightapp.data.source.city.CityDataSource
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityDataSource: CityDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val cityListMapperImpl: ListMapper<City, LocationEntity>
) : CityRepository {
    override fun getAllCity(): Flow<NetworkResult<List<LocationEntity>>> =
        flow {
            emit(NetworkResult.Loading)
            when (val response = cityDataSource.getAllCity()) {
                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(response.exception))
                }

                NetworkResult.Loading -> Unit
                is NetworkResult.Success -> {
                    emit(
                        NetworkResult.Success(
                            cityListMapperImpl.map(response.data)
                        )
                    )
                }
            }
        }.flowOn(ioDispatcher)
}