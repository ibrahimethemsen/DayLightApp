package com.daylightapp.common.weather


import com.squareup.moshi.Json

data class Rain(
    @Json(name = "3h")
    val h: Double?
)