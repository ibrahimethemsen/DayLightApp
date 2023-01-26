package com.ibrahimethemsen.daylightapp.data.repository.datastore

import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
import com.ibrahimethemsen.daylightapp.data.source.datastore.DataStoreDataSource
import com.ibrahimethemsen.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource : DataStoreDataSource
) : DataStoreRepository {
    override suspend fun writeCityDataStore(lat: String, lon: String,name : String) {
        dataStoreDataSource.writeCityDataStore(lat,lon,name)
    }

    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStoreDataSource.readCityDataStore
}