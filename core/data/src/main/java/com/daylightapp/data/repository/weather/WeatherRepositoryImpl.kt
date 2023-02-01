package com.daylightapp.data.repository.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.fiveday.Response
import com.daylightapp.data.common.Constants.DAY_MONTH_FORMAT
import com.daylightapp.data.common.Constants.DAY_MONTH_YEAR_FORMAT
import com.daylightapp.data.di.IoDispatcher
import com.daylightapp.data.source.weather.WeatherDataSource
import com.daylightapp.domain.common.Constants.HOUR_MINUTE_FORMAT
import com.daylightapp.domain.common.currentDateFormat
import com.daylightapp.domain.common.kelvinToCelcius
import com.daylightapp.domain.common.milToKmSpeed
import com.daylightapp.domain.common.toDateFormat
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.domain.mapper.ListMapper
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
    private val fiveDayWeatherMapper: ListMapper<Response, FiveDayWeatherEntity>,
    private val detailFiveDayWeatherMapper: ListMapper<Response, DetailFiveDayWeatherEntity>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    override fun getFiveDayThreeHoursWeatherForecast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<FiveDayWeatherEntity>>> =
        flow {
            emit(NetworkResult.Loading)
            when (val response = weatherDataSource.getFiveDayWeatherForecast(lat, lon)) {
                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(response.exception))
                }
                NetworkResult.Loading -> {
                    emit(NetworkResult.Loading)
                }
                is NetworkResult.Success -> {
                    emit(NetworkResult.Success(
                        fiveDayWeatherMapper.map(
                            response.data.list?.filter {
                                DAY_MONTH_FORMAT.currentDateFormat() == it.dt?.toDateFormat(DAY_MONTH_FORMAT)
                            }
                        )
                    ))
                }
            }
        }.flowOn(ioDispatcher)

    override fun getCurrentWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<CurrentWeatherEntity>> = flow {
        emit(NetworkResult.Loading)
        when (val response = weatherDataSource.getCurrentDayWeather(lat, lon)) {
            is NetworkResult.Error -> {
                emit(NetworkResult.Error(response.exception))
            }
            NetworkResult.Loading -> {
                emit(NetworkResult.Loading)
            }
            is NetworkResult.Success -> {
                val sunset = response.data.sys?.sunset?.toDateFormat(HOUR_MINUTE_FORMAT)
                val sunrise = response.data.sys?.sunrise?.toDateFormat(HOUR_MINUTE_FORMAT)
                val date = response.data.dt?.toDateFormat(DAY_MONTH_YEAR_FORMAT)
                val windSpeed = response.data.wind?.speed?.milToKmSpeed()
                val celcius = response.data.main?.temp?.kelvinToCelcius()
                val imageWeather = response.data.weather?.get(0)
                emit(NetworkResult.Success(
                    CurrentWeatherEntity(
                        sunset = sunset,
                        sunrise = sunrise,
                        currentDate = date,
                        windSpeed = windSpeed.toString(),
                        currentCelcius = celcius,
                        imageIconId = imageWeather?.icon,
                        description = imageWeather?.description?.replaceFirstChar {
                            it.uppercase()
                        }
                    )
                ))
            }
        }
    }.flowOn(ioDispatcher)

    override fun getFiveDayDetailWeatherForeCast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<DetailFiveDayWeatherEntity>>> =
        flow {
            emit(NetworkResult.Loading)
            when (val response = weatherDataSource.getFiveDayWeatherForecast(lat, lon)) {
                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(response.exception))
                }
                NetworkResult.Loading -> {
                    emit(NetworkResult.Loading)
                }
                is NetworkResult.Success -> {
                    emit(
                        NetworkResult.Success(
                            detailFiveDayWeatherMapper.map(response.data.list)
                        )
                    )
                }
            }
        }.flowOn(ioDispatcher)
}

