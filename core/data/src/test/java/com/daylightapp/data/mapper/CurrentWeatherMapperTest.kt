package com.daylightapp.data.mapper

import com.daylightapp.data.common.Constants
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.repository.weather.testCurrentWeather
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CurrentWeatherMapperTest {
    private lateinit var currentWeatherMapperImpl: CurrentWeatherMapperImpl

    @Before
    fun setup(){
        currentWeatherMapperImpl = CurrentWeatherMapperImpl()
    }

    @Test
    fun mapper(){
        val mapper = currentWeatherMapperImpl.map(testCurrentWeather).sunrise
        val list = testCurrentWeather.sys?.sunrise?.toDateFormat(Constants.HOUR_MINUTE_FORMAT)
        assertThat(list).isEqualTo(mapper)
    }
}