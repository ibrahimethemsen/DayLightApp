package com.daylightapp.domain.usecase.datastore.read

import com.daylightapp.common.datastore.LocationEntity

import com.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CityDataStoreUseCaseImpl @Inject constructor(
    private val dataStoreRepository : DataStoreRepository
) : CityDataStoreUseCase {
    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStoreRepository.readCityDataStore

}