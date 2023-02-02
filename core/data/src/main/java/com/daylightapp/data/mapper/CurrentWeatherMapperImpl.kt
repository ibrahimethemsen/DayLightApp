package com.daylightapp.data.mapper

import com.daylightapp.data.common.Constants
import com.daylightapp.data.common.kelvinToCelcius
import com.daylightapp.data.common.milToKmSpeed
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.dto.weather.current.CurrentWeather
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherMapperImpl @Inject constructor() :
    Mapper<CurrentWeather, CurrentWeatherEntity> {
    override fun map(input: CurrentWeather?): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            input?.sys?.sunset?.toDateFormat(Constants.HOUR_MINUTE_FORMAT),
            input?.sys?.sunrise?.toDateFormat(Constants.HOUR_MINUTE_FORMAT),
            input?.dt?.toDateFormat(Constants.DAY_MONTH_YEAR_FORMAT),
            input?.wind?.speed?.milToKmSpeed(),
            input?.main?.temp?.kelvinToCelcius(),
            input?.weather?.get(0)?.icon,
            input?.weather?.get(0)?.description?.replaceFirstChar { it.uppercase() }
        )
    }
}
