package com.daylightapp.domain.entity.weather

data class DetailFiveDayWeatherEntity(
    val tempCelcius : String?,
    val tempFeelsLike : String?,
    val iconId : String?,
    val weatherParameter : String?,
    val windSpeed : String?,
    val windDeg : String?,
    val humidity : String?
)
