package com.daylightapp.data.dto.weather.fiveday


import com.squareup.moshi.Json

data class Rain(
    @Json(name = "3h")
    val h: Double?
)