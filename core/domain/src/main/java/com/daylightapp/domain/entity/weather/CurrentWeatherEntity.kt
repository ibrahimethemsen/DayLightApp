package com.daylightapp.domain.entity.weather

data class CurrentWeatherEntity(
    val sunset : String? ,
    val sunrise : String? ,
    val currentDate : String? ,
    val windSpeed : String?,
    val currentCelcius : String? ,
    val imageIconId : String? ,
    val description : String?
)
