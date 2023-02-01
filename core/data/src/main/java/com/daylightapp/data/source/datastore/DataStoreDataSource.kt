package com.daylightapp.data.source.datastore

import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.entity.quote.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {
    //city
    suspend fun writeCityDataStore(lat : String,lon : String,name : String,plate : String)
    val readCityDataStore : Flow<LocationEntity>
    //nav start
    suspend fun writeNavStartDestination(destination : String)
    val readNavStartDestination : Flow<String>

    //current date
    suspend fun writeCurrentDate(currentDate : String)
    val readCurrentDate : Flow<String>

    suspend fun writeLocalQuote(quote : String,author : String)
    val readLocalQuote : Flow<QuoteEntity>
}