package com.ibrahimethemsen.daylightapp.domain.repository.datastore

import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun writeCityDataStore(lat : String,lon : String,name : String)
    val readCityDataStore : Flow<LocationEntity>
    suspend fun writeNavDestination(destination : String)
    val readNavDestination : Flow<String>

}