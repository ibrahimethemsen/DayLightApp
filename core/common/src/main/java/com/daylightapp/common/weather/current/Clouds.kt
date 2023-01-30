package com.daylightapp.common.weather.current


import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    val all: Int?
)