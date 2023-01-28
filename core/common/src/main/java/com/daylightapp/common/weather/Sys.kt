package com.daylightapp.common.weather


import com.squareup.moshi.Json

data class Sys(
    @Json(name = "pod")
    val pod: String?
)