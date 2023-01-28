package com.daylightapp.domain.usecase.datastore.read

import kotlinx.coroutines.flow.Flow


interface NavStartDestinationUseCase {
    val readNavStartDestination : Flow<String>
}