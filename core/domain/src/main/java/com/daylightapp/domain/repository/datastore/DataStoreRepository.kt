package com.daylightapp.domain.repository.datastore


import com.daylightapp.common.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun writeCityDataStore(lat : String,lon : String,name : String)
    val readCityDataStore : Flow<LocationEntity>
    suspend fun writeNavDestination(destination : String)
    val readNavDestination : Flow<String>

}