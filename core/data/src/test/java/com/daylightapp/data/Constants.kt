package com.daylightapp.data

import com.daylightapp.data.dto.city.City
import com.daylightapp.data.dto.weather.fiveday.Main
import com.daylightapp.data.dto.weather.fiveday.Response
import com.daylightapp.data.dto.weather.fiveday.Weather
import com.daylightapp.data.dto.weather.fiveday.Wind

object Constants {
    const val FILE_NAME_CITY_LIST_RESPONSE = "CityListResponse.json"
    const val FILE_NAME_RANDOM_QUOTE_RESPONSE = "RandomQuoteResponse.json"
    const val FILE_NAME_FIVE_DAY_WEATHER_RESPONSE = "FiveDayWeatherResponse.json"
    const val FILE_NAME_CURRENT_DAY_WEATHER_RESPONSE = "CurrentWeatherResponse.json"
    const val MIN_LENGHT = 150
    const val MAX_LENGHT = 300
    const val LAT = "38.6939"
    const val LON = "34.6857"
}

val cityDto = City(
    id = 50,
    name = "Nevşehir",
    latitude = "38.6939",
    longitude = "34.6857",
    population = 286767,
    region = "İç Anadolu"
)

val cityList = mutableListOf(cityDto)

val fiveDayWeatherDto = Response(
    dt = 1675285200,
    main = Main(
        null,
        null,
        83,
        null,
        null,
        271.99,
        null,
        null,
        null,
    ),
    weather = mutableListOf(Weather(
        null,
        "13n",
        null,
        "Snow"
    )),
    clouds = null,
    visibility = 6926,
    wind = Wind(
        334,
        null,
        4.18
    ),
    pop = 0.47,
    snow = null,
    sys = null,
    dtTxt = "2023-02-01 21:00:00",
    rain = null
)

val fiveDayWeatherList = mutableListOf(fiveDayWeatherDto)