package com.ibrahimethemsen.daylightapp.data.dto.weather


import com.squareup.moshi.Json

data class Sys(
    @Json(name = "pod")
    val pod: String?
)