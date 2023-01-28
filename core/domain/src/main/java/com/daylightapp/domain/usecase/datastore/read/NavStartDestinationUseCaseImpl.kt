package com.daylightapp.domain.usecase.datastore.read

import com.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NavStartDestinationUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : NavStartDestinationUseCase {
    override val readNavStartDestination: Flow<String>
        get() = dataStoreRepository.readNavDestination
}