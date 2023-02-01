package com.daylightapp.data.mapper

import com.daylightapp.data.dto.city.City
import com.daylightapp.domain.entity.city.LocationEntity
import javax.inject.Inject

class CityListMapperImpl @Inject constructor() : ListMapper<City, LocationEntity> {
    override fun map(input: List<City>?): List<LocationEntity> {
        return input?.map {
            LocationEntity(
                lon = it.longitude.toString(),
                lat = it.latitude.toString(),
                name = it.name.toString(),
                plate = it.id.toString()
            )
        } ?: emptyList()
    }
}