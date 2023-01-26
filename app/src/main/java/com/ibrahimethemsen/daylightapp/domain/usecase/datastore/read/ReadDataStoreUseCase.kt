package com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read

import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
import kotlinx.coroutines.flow.Flow

interface ReadDataStoreUseCase {
    val readCityDataStore : Flow<LocationEntity>
}