package com.ibrahimethemsen.daylightapp.data.dto.weather


import com.squareup.moshi.Json

data class WeatherResponse(
    @Json(name = "city")
    val city: City?,
    @Json(name = "cnt")
    val cnt: Int?,
    @Json(name = "cod")
    val cod: String?,
    @Json(name = "list")
    val list: List<Response>?,
    @Json(name = "message")
    val message: Int?
)