package com.daylightapp.data.repository.datastore

import com.daylightapp.common.datastore.LocationEntity
import com.daylightapp.data.source.datastore.DataStoreDataSource
import com.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    override suspend fun writeCityDataStore(lat: String, lon: String, name: String) =
        dataStoreDataSource.writeCityDataStore(lat, lon, name)


    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStoreDataSource.readCityDataStore

    override suspend fun writeNavDestination(destination: String) =
        dataStoreDataSource.writeNavStartDestination(destination)

    override val readNavDestination: Flow<String>
        get() = dataStoreDataSource.readNavStartDestination

}