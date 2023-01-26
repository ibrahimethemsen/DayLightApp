package com.ibrahimethemsen.daylightapp.data.source.datastore

import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {
    suspend fun writeCityDataStore(lat : String,lon : String,name : String)
    val readCityDataStore : Flow<LocationEntity>
}