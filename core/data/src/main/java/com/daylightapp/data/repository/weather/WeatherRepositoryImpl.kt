package com.daylightapp.data.repository.weather

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.weather.fiveday.WeatherResponse
import com.daylightapp.data.source.weather.WeatherDataSource
import com.daylightapp.domain.common.Constants.HOUR_MINUTE_FORMAT
import com.daylightapp.domain.common.toDateFormat
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.DecimalFormat
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override fun getFiveDayWeatherForecast(
        lat: String,
        lon: String
    ): Flow<NetworkResult<WeatherResponse>> = flow<NetworkResult<WeatherResponse>> {
        emit(NetworkResult.Loading)
    }.catch {
        emit(NetworkResult.Error(it))
    }.map {
        weatherDataSource.getFiveDayWeatherForecast(lat, lon)
    }

    override fun getCurrentWeather(lat: String, lon: String): Flow<NetworkResult<CurrentWeatherEntity>> = flow<NetworkResult<CurrentWeatherEntity>> {
        emit(NetworkResult.Loading)
    }.catch {
        emit(NetworkResult.Error(it))
    }.map {
        when(val response = weatherDataSource.getCurrentDayWeather(lat, lon)){
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
                val windSpeed = response.data.wind?.speed?.let {speed ->
                    //miles/hour -> km/s
                    speed * 1.60934
                }
                val decimalFormat = DecimalFormat("#.##")
                val decimalFormatSpeed = decimalFormat.format(windSpeed)
                val celcius = response.data.main?.temp?.minus(273.15)?.toInt()
                val imageWeather = response.data.weather?.get(0)
                NetworkResult.Success(
                    CurrentWeatherEntity(
                        sunset = sunset,
                        sunrise = sunrise,
                        currentDate = date,
                        windSpeed = decimalFormatSpeed.toString().plus(" km/s"),
                        currentCelcius = celcius.toString().plus("°C"),
                        imageIconId = imageWeather?.icon,
                        description = imageWeather?.description?.replaceFirstChar {
                            it.uppercase()
                        }
                    )
                )
            }
        }
    }
}

