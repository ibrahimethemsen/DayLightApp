package com.daylightapp.data.dto.weather.fiveday


import com.squareup.moshi.Json

data class Sys(
    @Json(name = "pod")
    val pod: String?
)