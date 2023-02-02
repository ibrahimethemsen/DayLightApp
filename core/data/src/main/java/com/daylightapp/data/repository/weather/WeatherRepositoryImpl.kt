package com.daylightapp.data.repository.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.common.Constants.DAY_MONTH_FORMAT
import com.daylightapp.data.common.currentDateFormat
import com.daylightapp.data.common.toDateFormat
import com.daylightapp.data.di.IoDispatcher
import com.daylightapp.data.dto.weather.current.CurrentWeather
import com.daylightapp.data.dto.weather.fiveday.Response
import com.daylightapp.data.mapper.ListMapper
import com.daylightapp.data.mapper.Mapper
import com.daylightapp.data.source.weather.WeatherDataSource
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
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
    private val currentWeatherMapper: Mapper<CurrentWeather, CurrentWeatherEntity>,
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
                                DAY_MONTH_FORMAT.currentDateFormat() == it.dt?.toDateFormat(
                                    DAY_MONTH_FORMAT
                                )
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
                emit(
                    NetworkResult.Success(
                        currentWeatherMapper.map(response.data)
                    )
                )
                println("response : ${response.data}")
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

