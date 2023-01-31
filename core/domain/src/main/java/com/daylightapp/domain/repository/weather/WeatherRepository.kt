package com.daylightapp.domain.repository.weather


import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getFiveDayThreeHoursWeatherForecast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<FiveDayWeatherEntity>>>

    fun getCurrentWeather(
        lat : String,
        lon : String
    ) : Flow<NetworkResult<CurrentWeatherEntity>>

    fun getFiveDayDetailWeatherForeCast(
        lat : String,
        lon : String
    ) : Flow<NetworkResult<List<DetailFiveDayWeatherEntity>>>
}