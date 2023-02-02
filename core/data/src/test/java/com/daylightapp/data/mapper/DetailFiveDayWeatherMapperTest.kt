package com.daylightapp.data.mapper

import com.daylightapp.data.common.Constants.CARD_DATE_FORMAT
import com.daylightapp.data.common.kelvinToCelcius
import com.daylightapp.data.common.milToKmSpeed
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.testFiveDayWeatherList
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DetailFiveDayWeatherMapperTest {
    private lateinit var detailWeatherMapper : DetailFiveDayWeatherMapperImpl
    private var detailWeatherList = testFiveDayWeatherList.first()
    private lateinit var detailMapperList : DetailFiveDayWeatherEntity
    @Before
    fun setup(){
        detailWeatherMapper = DetailFiveDayWeatherMapperImpl()
        detailMapperList = detailWeatherMapper.map(testFiveDayWeatherList).first()
    }

    @Test
    fun when_temp_is_mapping_detail_weather(){
        val listTemp = detailWeatherList.main?.temp?.kelvinToCelcius()
        val mapperTemp = detailMapperList.tempCelcius
        assertThat(listTemp).isEqualTo(mapperTemp)
    }

    @Test
    fun when_date_is_mapping_weather(){
        val listDate = detailWeatherList.dt?.toDateFormat(CARD_DATE_FORMAT)
        val mapperDate = detailMapperList.date
        assertThat(listDate).isEqualTo(mapperDate)
    }

    @Test
    fun when_iconId_is_mapping_weather(){
        val listIconId = detailWeatherList.weather?.first()?.icon
        val mapperIconId = detailMapperList.iconId
        assertThat(listIconId).isEqualTo(mapperIconId)
    }

    @Test
    fun when_weatherParameter_is_mapping_weather(){
        val listParameter = detailWeatherList.weather?.first()?.main
        val mapperParameter = detailMapperList.weatherParameter
        assertThat(listParameter).isEqualTo(mapperParameter)
    }

    @Test
    fun when_windSpeed_is_mapping_weather(){
        val listWindSpeed = detailWeatherList.wind?.speed?.milToKmSpeed()
        val mapperWindSpeed = detailMapperList.windSpeed
        assertThat(listWindSpeed).isEqualTo(mapperWindSpeed)
    }

    @Test
    fun when_windDeg_is_mapping_weather(){
        val listWindDeg = detailWeatherList.wind?.deg?.toString()
        val mapperWindDeg = detailMapperList.windDeg
        assertThat(listWindDeg).isEqualTo(mapperWindDeg)
    }

    @Test
    fun when_humidity_is_mapping_weather(){
        val listHumidity = detailWeatherList.main?.humidity?.toString()
        val mapperHumidity = detailMapperList.humidity
        assertThat(listHumidity).isEqualTo(mapperHumidity)
    }
}