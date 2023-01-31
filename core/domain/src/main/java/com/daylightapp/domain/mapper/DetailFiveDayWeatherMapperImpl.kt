package com.daylightapp.domain.mapper

import com.daylightapp.common.weather.fiveday.Response
import com.daylightapp.domain.common.Constants.CARD_DATE_FORMAT
import com.daylightapp.domain.common.kelvinToCelcius
import com.daylightapp.domain.common.milToKmSpeed
import com.daylightapp.domain.common.toDateFormat
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import javax.inject.Inject

class DetailFiveDayWeatherMapperImpl @Inject constructor() : ListMapper<Response,DetailFiveDayWeatherEntity> {
    override fun map(input: List<Response>?): List<DetailFiveDayWeatherEntity> {
        return input?.map {
            DetailFiveDayWeatherEntity(
                tempCelcius = it.main?.temp?.kelvinToCelcius(),
                date = it.dt?.toDateFormat(CARD_DATE_FORMAT),
                iconId = it.weather?.get(0)?.icon,
                weatherParameter = it.weather?.get(0)?.main,
                windSpeed = it.wind?.speed?.milToKmSpeed(),
                windDeg = it.wind?.deg?.toString(),
                humidity = it.main?.humidity?.toString()
            )
        } ?: emptyList()
    }
}