package com.daylightapp.data.repository.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.fiveday.Response
import com.daylightapp.data.source.weather.WeatherDataSource
import com.daylightapp.domain.common.Constants.HOUR_MINUTE_FORMAT
import com.daylightapp.domain.common.kelvinToCelcius
import com.daylightapp.domain.common.milToKmSpeed
import com.daylightapp.domain.common.toDateFormat
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.domain.mapper.ListMapper
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
    private val fiveDayWeatherMapper: ListMapper<Response, FiveDayWeatherEntity>,
    private val detailFiveDayWeatherMapper : ListMapper<Response,DetailFiveDayWeatherEntity>
) : WeatherRepository {
    override fun getFiveDayThreeHoursWeatherForecast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<FiveDayWeatherEntity>>> =
        flow<NetworkResult<List<FiveDayWeatherEntity>>> {
            emit(NetworkResult.Loading)
        }.catch {
            emit(NetworkResult.Error(it))
        }.map {
            when (val response = weatherDataSource.getFiveDayWeatherForecast(lat, lon)) {
                is NetworkResult.Error -> {
                    NetworkResult.Error(response.exception)
                }
                NetworkResult.Loading -> {
                    NetworkResult.Loading
                }
                is NetworkResult.Success -> {
                    NetworkResult.Success(
                        fiveDayWeatherMapper.map(
                            response.data.list
                        )
                    )
                }
            }
        }

    override fun getCurrentWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<CurrentWeatherEntity>> = flow<NetworkResult<CurrentWeatherEntity>> {
        emit(NetworkResult.Loading)
    }.catch {
        emit(NetworkResult.Error(it))
    }.map {
        when (val response = weatherDataSource.getCurrentDayWeather(lat, lon)) {
            is NetworkResult.Error -> {
                NetworkResult.Error(response.exception)
            }
            NetworkResult.Loading -> {
                NetworkResult.Loading
            }
            is NetworkResult.Success -> {
                val sunset = response.data.sys?.sunset?.toDateFormat(HOUR_MINUTE_FORMAT)
                val sunrise = response.data.sys?.sunrise?.toDateFormat(HOUR_MINUTE_FORMAT)
                val date = response.data.dt?.toDateFormat("dd MMM yyyy")
                val windSpeed = response.data.wind?.speed?.milToKmSpeed()
                val celcius = response.data.main?.temp?.kelvinToCelcius()
                val imageWeather = response.data.weather?.get(0)
                NetworkResult.Success(
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
                )
            }
        }
    }

    override fun getFiveDayDetailWeatherForeCast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<List<DetailFiveDayWeatherEntity>>> = flow<NetworkResult<List<DetailFiveDayWeatherEntity>>> {
        emit(NetworkResult.Loading)
    }.catch {
        emit(NetworkResult.Error(it))
    }.map {
        when(val response = weatherDataSource.getFiveDayWeatherForecast(lat, lon)){
            is NetworkResult.Error -> {
                NetworkResult.Error(response.exception)
            }
            NetworkResult.Loading -> {
                NetworkResult.Loading
            }
            is NetworkResult.Success -> {
                NetworkResult.Success(
                    detailFiveDayWeatherMapper.map(response.data.list)
                )
            }
        }
    }
}

