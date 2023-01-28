package com.daylightapp.domain.usecase.datastore.write

import com.daylightapp.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class WriteCityDataStoreUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : WriteCityDataStoreUseCase {
    override suspend fun invoke(lat: String, lon: String,name : String) {
        dataStoreRepository.writeCityDataStore(lat, lon,name)
    }
}