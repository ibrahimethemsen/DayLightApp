package com.daylightapp.domain

import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.entity.quote.QuoteEntity


val testCityEntity = LocationEntity(
    plate = "50",
    name = "Nevşehir",
    lat = "38.6939",
    lon = "34.6857"
)

val testCityEntityList = mutableListOf(testCityEntity)

val testQuoteEntity = QuoteEntity(
    "ethem şen",
    "ibrahim"
)
enum class TestResponseEnum {
    ERROR,
    SUCCESS,
    LOADING
}