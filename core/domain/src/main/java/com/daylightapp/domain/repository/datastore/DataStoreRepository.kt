package com.daylightapp.domain.repository.datastore


import com.daylightapp.domain.entity.city.LocationEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    //city
    suspend fun writeCityDataStore(lat: String, lon: String, name: String,plate : String)
    val readCityDataStore: Flow<LocationEntity>

    //destination
    suspend fun writeNavDestination(destination: String)
    val readNavDestination: Flow<String>
}