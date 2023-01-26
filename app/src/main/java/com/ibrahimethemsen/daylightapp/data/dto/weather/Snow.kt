package com.ibrahimethemsen.daylightapp.data.dto.weather


import com.squareup.moshi.Json

data class Snow(
    @Json(name = "3h")
    val h: Double?
)