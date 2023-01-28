package com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write

interface WriteNavStartDestinationUseCase {
    suspend operator fun invoke(destination : String)
}