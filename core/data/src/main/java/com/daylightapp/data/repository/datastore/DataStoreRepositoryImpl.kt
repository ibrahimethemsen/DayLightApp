package com.daylightapp.data.repository.datastore

import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.data.source.datastore.DataStoreDataSource
import com.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    override suspend fun writeCityDataStore(lat: String, lon: String, name: String,plate : String) =
        dataStoreDataSource.writeCityDataStore(lat, lon, name,plate)


    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStoreDataSource.readCityDataStore

    override suspend fun writeNavDestination(destination: String) =
        dataStoreDataSource.writeNavStartDestination(destination)

    override val readNavDestination: Flow<String>
        get() = dataStoreDataSource.readNavStartDestination

}