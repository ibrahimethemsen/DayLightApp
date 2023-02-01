package com.daylightapp.data.mapper


import com.daylightapp.data.common.Constants.CARD_DATE_FORMAT
import com.daylightapp.data.common.kelvinToCelcius
import com.daylightapp.data.common.milToKmSpeed
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.dto.weather.fiveday.Response
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

