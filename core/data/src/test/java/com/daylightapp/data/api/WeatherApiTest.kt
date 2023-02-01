package com.daylightapp.data.api

import com.daylightapp.data.Constants.FILE_NAME_CURRENT_DAY_WEATHER_RESPONSE
import com.daylightapp.data.Constants.FILE_NAME_FIVE_DAY_WEATHER_RESPONSE
import com.daylightapp.data.Constants.LAT
import com.daylightapp.data.Constants.LON
import com.daylightapp.data.api.weather.WeatherApi
import com.daylightapp.data.common.Constants.API_LANGUAGE
import com.daylightapp.data.common.Constants.WEATHER_API_KEY
import com.daylightapp.data.enqueueResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApiTest {
    private lateinit var weatherApi: WeatherApi
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        weatherApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

    }

    @Test
    fun when_fiveDayWeatherList_is_notNull() = runBlocking {
        mockWebServer.enqueueResponse(FILE_NAME_FIVE_DAY_WEATHER_RESPONSE)
        val response = weatherApi.fiveDayWeatherForecast(LAT, LON)
        assertThat(response).isNotNull()
    }

    @Test
    fun when_currentDayWeather_is_notNull() = runBlocking {
        mockWebServer.enqueueResponse(FILE_NAME_CURRENT_DAY_WEATHER_RESPONSE)
        val response = weatherApi.currentDayWeather(LAT, LON)
        assertThat(response).isNotNull()
    }

    @Test
    fun when_fiveDayWeather_is_requestPath() = runBlocking {
        mockWebServer.enqueueResponse(FILE_NAME_FIVE_DAY_WEATHER_RESPONSE)
        val response = weatherApi.fiveDayWeatherForecast(LAT, LON)
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/data/2.5/forecast?lat=${LAT}&lon=${LON}&lang=${API_LANGUAGE}&appid=${WEATHER_API_KEY}")
    }

    @Test
    fun when_currentDayWeather_is_requestPath() = runBlocking {
        mockWebServer.enqueueResponse(FILE_NAME_CURRENT_DAY_WEATHER_RESPONSE)
        val response = weatherApi.currentDayWeather(LAT, LON)
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/data/2.5/weather?lat=${LAT}&lon=${LON}&lang=${API_LANGUAGE}&appid=${WEATHER_API_KEY}")
    }

    @Test
    fun when_fiveDayWeather_is_firstWeather() = runBlocking {
        mockWebServer.enqueueResponse(FILE_NAME_FIVE_DAY_WEATHER_RESPONSE)
        val response = weatherApi.fiveDayWeatherForecast(LAT, LON)
        val code = response.cod
        val sunrise = response.city?.sunrise
        assertThat(code).isEqualTo("200")
        assertThat(sunrise).isEqualTo(1675226856)
    }

    @Test
    fun when_currentDayWeather_is_currentWeather() = runBlocking {
        mockWebServer.enqueueResponse(FILE_NAME_CURRENT_DAY_WEATHER_RESPONSE)
        val response = weatherApi.currentDayWeather(LAT, LON)
        val id = response.id
        val weatherId = response.weather?.get(0)?.id
        assertThat(id).isEqualTo(313658)
        assertThat(weatherId).isEqualTo(804)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }
}