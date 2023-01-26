package com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write

import com.ibrahimethemsen.daylightapp.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class WriteDataStoreUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : WriteDataStoreUseCase {
    override suspend fun invoke(lat: String, lon: String,name : String) {
        dataStoreRepository.writeCityDataStore(lat, lon,name)
    }
}