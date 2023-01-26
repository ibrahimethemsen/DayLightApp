package com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read

import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
import com.ibrahimethemsen.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataStoreUseCaseImpl @Inject constructor(
    private val dataStoreRepository : DataStoreRepository
) : ReadDataStoreUseCase {
    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStoreRepository.readCityDataStore

}