package com.daylightapp.domain.usecase.datastore.read

import com.daylightapp.domain.entity.city.LocationEntity
import kotlinx.coroutines.flow.Flow

interface CityDataStoreUseCase {
    val readCityDataStore : Flow<LocationEntity>
}