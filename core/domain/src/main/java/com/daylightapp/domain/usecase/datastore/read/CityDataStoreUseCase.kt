package com.daylightapp.domain.usecase.datastore.read

import com.daylightapp.common.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface CityDataStoreUseCase {
    val readCityDataStore : Flow<LocationEntity>
}