package com.daylightapp.domain.usecase.datastore.write

import com.daylightapp.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class WriteNavStartDestinationUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : WriteNavStartDestinationUseCase {
    override suspend fun invoke(destination: String) {
        dataStoreRepository.writeNavDestination(destination)
    }

}