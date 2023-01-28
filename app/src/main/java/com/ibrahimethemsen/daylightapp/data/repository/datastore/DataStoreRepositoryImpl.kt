package com.ibrahimethemsen.daylightapp.data.repository.datastore

import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
import com.ibrahimethemsen.daylightapp.data.source.datastore.DataStoreDataSource
import com.ibrahimethemsen.daylightapp.di.IoDispatcher
import com.ibrahimethemsen.daylightapp.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DataStoreRepository {
    override suspend fun writeCityDataStore(lat: String, lon: String, name: String) {
        withContext(ioDispatcher) {
            dataStoreDataSource.writeCityDataStore(lat, lon, name)
        }
    }

    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStoreDataSource.readCityDataStore

    override suspend fun writeNavDestination(destination: String) {
        withContext(ioDispatcher) {
            dataStoreDataSource.writeNavStartDestination(destination)
        }
    }

    override val readNavDestination: Flow<String>
        get() = dataStoreDataSource.readNavStartDestination
}