package com.daylightapp.domain.repository.datastore


import com.daylightapp.common.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    //city
    suspend fun writeCityDataStore(lat: String, lon: String, name: String)
    val readCityDataStore: Flow<LocationEntity>

    //destination
    suspend fun writeNavDestination(destination: String)
    val readNavDestination: Flow<String>
}