package com.daylightapp.domain.mapper

import com.daylightapp.common.weather.fiveday.Response
import com.daylightapp.domain.common.Constants.CARD_DATE_FORMAT
import com.daylightapp.domain.common.kelvinToCelcius
import com.daylightapp.domain.common.milToKmSpeed
import com.daylightapp.domain.common.toDateFormat
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import javax.inject.Inject

class FiveDayWeatherMapperImpl @Inject constructor(): ListMapper<Response, FiveDayWeatherEntity> {
    override fun map(input: List<Response>?): List<FiveDayWeatherEntity> {
        return input?.map {
            FiveDayWeatherEntity(
                iconId = it.weather?.get(0)?.icon,
                celcius = it.main?.temp?.kelvinToCelcius(),
                date = it.dt?.toDateFormat(CARD_DATE_FORMAT),
                windSpeed = it.wind?.speed?.milToKmSpeed()
            )
        } ?: emptyList()
    }
}

