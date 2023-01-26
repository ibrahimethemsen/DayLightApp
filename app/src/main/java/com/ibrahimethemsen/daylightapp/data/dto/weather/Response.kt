package com.ibrahimethemsen.daylightapp.data.dto.weather


import com.squareup.moshi.Json

data class Response(
    @Json(name = "clouds")
    val clouds: Clouds?,
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "dt_txt")
    val dtTxt: String?,
    @Json(name = "main")
    val main: Main?,
    @Json(name = "pop")
    val pop: Double?,
    @Json(name = "rain")
    val rain: Rain?,
    @Json(name = "snow")
    val snow: Snow?,
    @Json(name = "sys")
    val sys: Sys?,
    @Json(name = "visibility")
    val visibility: Int?,
    @Json(name = "weather")
    val weather: List<Weather?>?,
    @Json(name = "wind")
    val wind: Wind?
)