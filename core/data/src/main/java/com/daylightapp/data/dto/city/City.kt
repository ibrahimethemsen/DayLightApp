package com.daylightapp.data.dto.city

import com.squareup.moshi.Json

data class City(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "latitude")
    val latitude: String?,
    @Json(name = "longitude")
    val longitude: String?,
    @Json(name = "population")
    val population: Int?,
    @Json(name = "region")
    val region: String?
)
