package com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write

interface WriteDataStoreUseCase {
    suspend operator fun invoke(lat : String,lon : String,name : String)
}