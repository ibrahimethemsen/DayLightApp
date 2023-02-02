package com.daylightapp.data.repository.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.TestResponseEnum
import com.daylightapp.data.dto.weather.current.CurrentWeather
import com.daylightapp.data.dto.weather.current.Clouds
import com.daylightapp.data.dto.weather.current.Coord
import com.daylightapp.data.dto.weather.current.Sys
import com.daylightapp.data.dto.weather.fiveday.Main
import com.daylightapp.data.dto.weather.fiveday.Weather
import com.daylightapp.data.dto.weather.fiveday.Wind
import com.daylightapp.data.mapper.CurrentWeatherMapperImpl
import com.daylightapp.data.mapper.DetailFiveDayWeatherMapperImpl
import com.daylightapp.data.mapper.FiveDayWeatherMapperImpl
import com.daylightapp.data.testFiveDayWeatherList
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherRepositoryImpl(
    private val fiveDayMapper: FiveDayWeatherMapperImpl,
    private val detailFiveDayWeatherMapperImpl: DetailFiveDayWeatherMapperImpl,
    private val currentWeatherMapper: CurrentWeatherMapperImpl
) : WeatherRepository {
    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest: TestResponseEnum) {
        this.testRequest = testRequest
    }

    override fun getFiveDayThreeHoursWeatherForecast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<FiveDayWeatherEntity>>> = flow {
        emit(NetworkResult.Loading)
        when (testRequest) {
            TestResponseEnum.ERROR -> emit(NetworkResult.Error(Exception("Bir hata oluştu")))
            TestResponseEnum.SUCCESS -> emit(
                NetworkResult.Success(
                    fiveDayMapper.map(
                        testFiveDayWeatherList
                    )
                )
            )

            TestResponseEnum.LOADING -> emit(NetworkResult.Loading)

        }
    }

    override fun getCurrentWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<CurrentWeatherEntity>> = flow {
        emit(NetworkResult.Loading)
        when (testRequest) {
            TestResponseEnum.ERROR -> emit(NetworkResult.Error(Exception("Bir hata oluştu")))
            TestResponseEnum.SUCCESS -> emit(
                NetworkResult.Success(
                    currentWeatherMapper.map(
                        testCurrentWeather
                    )
                )
            )

            TestResponseEnum.LOADING -> emit(NetworkResult.Loading)
        }
    }

    override fun getFiveDayDetailWeatherForeCast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<DetailFiveDayWeatherEntity>>> = flow {
        emit(NetworkResult.Loading)
        when (testRequest) {
            TestResponseEnum.ERROR -> emit(NetworkResult.Error(Exception("Bir hata oluştu")))
            TestResponseEnum.SUCCESS -> emit(
                NetworkResult.Success(
                    detailFiveDayWeatherMapperImpl.map(testFiveDayWeatherList)
                )
            )

            TestResponseEnum.LOADING -> emit(NetworkResult.Loading)

        }
    }
}
val testCurrentWeather = CurrentWeather(
    base = "stations",
    clouds = Clouds(all = 20),
    cod = 200,
    coord = Coord(lat = 38.6939, lon = 34.6857),
    dt = 1675359727,
    id = 313658,
    main = Main(
        feelsLike = null,
        humidity = 80,
        pressure = 1020,
        temp = 269.33,
        tempMax = null,
        tempMin = null,
        grndLevel = 2,
        seaLevel = 2,
        tempKf = 2.2
    ),
    name = "Gülşehir",
    sys = Sys(country = "TR", id = 6964, sunrise = 1675313203, sunset = 1675350180, type = 1),
    timezone = 10800,
    visibility = 10000,
    weather = listOf(Weather(description =" az bulutlu", icon = "02 n", id = 801, main = "20")) ,
    wind = Wind(deg = 310, speed = 1.54, gust = 15.2)
)