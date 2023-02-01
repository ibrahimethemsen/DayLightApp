package com.daylightapp.domain.usecase.datastore.write

interface WriteCityDataStoreUseCase {
    suspend operator fun invoke(lat : String,lon : String,name : String,plate : String)
}