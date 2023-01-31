package com.daylightapp.data.source.datastore

import com.daylightapp.common.datastore.LocalQuote
import com.daylightapp.common.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {
    //city
    suspend fun writeCityDataStore(lat : String,lon : String,name : String)
    val readCityDataStore : Flow<LocationEntity>
    //nav start
    suspend fun writeNavStartDestination(destination : String)
    val readNavStartDestination : Flow<String>

    //current date
    suspend fun writeCurrentDate(currentDate : String,quote : String,author : String)
    val readCurrentDate :Flow<LocalQuote>
}