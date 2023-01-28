package com.daylightapp.common.weather


import com.squareup.moshi.Json

data class Snow(
    @Json(name = "3h")
    val h: Double?
)