package com.ibrahimethemsen.daylightapp.data.dto.weather


import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    val all: Int?
)