package com.daylightapp.data.dto.weather.fiveday


import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    val all: Int?
)