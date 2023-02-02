package com.daylightapp.data.mapper

import com.daylightapp.data.common.Constants.CARD_DATE_FORMAT
import com.daylightapp.data.common.kelvinToCelcius
import com.daylightapp.data.common.milToKmSpeed
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.fiveDayWeatherList
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FiveDayWeatherMapperTest {
    private lateinit var fiveDayWeatherMapper : FiveDayWeatherMapperImpl
    private val weatherList = fiveDayWeatherList.first()
    private lateinit var weatherMapper : FiveDayWeatherEntity
    @Before
    fun setup(){
        fiveDayWeatherMapper = FiveDayWeatherMapperImpl()
        weatherMapper = fiveDayWeatherMapper.map(fiveDayWeatherList).first()
    }

    @Test
    fun when_iconId_is_mapping_weather(){
        val weatherListIconId = weatherList.weather?.first()?.icon
        val weatherMapperIconId = weatherMapper.iconId
        assertThat(weatherListIconId).isEqualTo(weatherMapperIconId)
    }

    @Test
    fun when_celcius_is_mapping_weather(){
        val weatherListTemp = weatherList.main?.temp?.kelvinToCelcius()
        val weatherMapperTemp = weatherMapper.celcius
        assertThat(weatherListTemp).isEqualTo(weatherMapperTemp)
    }

    @Test
    fun when_date_is_mapping_weather(){
        val weatherListDate = weatherList.dt?.toDateFormat(CARD_DATE_FORMAT)
        val weatherMapperDate = weatherMapper.date
        assertThat(weatherListDate).isEqualTo(weatherMapperDate)
    }

    @Test
    fun when_speed_is_mapping_weather(){
        val weatherListSpeed = weatherList.wind?.speed?.milToKmSpeed()
        val weatherMapperSpeed = weatherMapper.windSpeed
        assertThat(weatherListSpeed).isEqualTo(weatherMapperSpeed)
    }
}