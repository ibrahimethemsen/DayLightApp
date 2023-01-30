package com.daylightapp.common.weather.fiveday


import com.squareup.moshi.Json

data class Snow(
    @Json(name = "3h")
    val h: Double?
)