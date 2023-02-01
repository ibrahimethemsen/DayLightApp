package com.daylightapp.data.mapper

import com.daylightapp.data.common.Constants.CARD_DATE_FORMAT
import com.daylightapp.data.common.kelvinToCelcius
import com.daylightapp.data.common.milToKmSpeed
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.dto.weather.fiveday.Response
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import javax.inject.Inject

class DetailFiveDayWeatherMapperImpl @Inject constructor() :
    ListMapper<Response, DetailFiveDayWeatherEntity> {
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