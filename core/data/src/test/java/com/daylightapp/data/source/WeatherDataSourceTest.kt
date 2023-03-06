package com.daylightapp.data.source

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.Constants.LAT
import com.daylightapp.data.Constants.LON
import com.daylightapp.data.api.weather.WeatherApi
import com.daylightapp.data.repository.weather.testCurrentWeather
import com.daylightapp.data.source.weather.WeatherDataSourceImpl
import com.daylightapp.data.testWeatherDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class WeatherDataSourceTest {
    @Mock
    private lateinit var weatherApi : WeatherApi

    private lateinit var weatherDataSource : WeatherDataSourceImpl
    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        weatherDataSource = WeatherDataSourceImpl(weatherApi)
    }

    @Test
    fun when_getFiveDay_is_success_response() = runBlocking{
        `when`(weatherApi.fiveDayWeatherForecast(LAT,LON)).thenReturn(
            testWeatherDto
        )
        val response = weatherDataSource.getFiveDayWeatherForecast(LAT, LON)
        assertThat(response).isInstanceOf(NetworkResult.Success::class.java)
    }

    @Test
    fun when_getFiveDay_is_error_response() = runBlocking {
        `when`(weatherApi.fiveDayWeatherForecast(LAT,LON)).thenReturn(
            null
        )
        val response = weatherDataSource.getFiveDayWeatherForecast(LAT, LON)
        assertThat(response).isInstanceOf(NetworkResult.Error::class.java)
    }

    @Test
    fun when_currentWeather_is_success_response() = runBlocking {
        `when`(weatherApi.currentDayWeather(LAT,LON)).thenReturn(
            testCurrentWeather
        )
        val response = weatherDataSource.getCurrentDayWeather(LAT,LON)
        assertThat(response).isInstanceOf(NetworkResult.Success::class.java)
    }

    @Test
    fun when_currentWeather_is_error_response() = runBlocking {
        `when`(weatherApi.currentDayWeather(LAT, LON)).thenReturn(
            null
        )
        val response = weatherDataSource.getCurrentDayWeather(LAT,LON)
        assertThat(response).isInstanceOf(NetworkResult.Error::class.java)
    }
}