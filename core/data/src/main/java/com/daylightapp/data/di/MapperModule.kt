package com.daylightapp.data.di

import com.daylightapp.data.dto.city.City
import com.daylightapp.data.dto.weather.current.CurrentWeather
import com.daylightapp.data.dto.weather.fiveday.Response
import com.daylightapp.data.mapper.CityListMapperImpl
import com.daylightapp.data.mapper.CurrentWeatherMapperImpl
import com.daylightapp.data.mapper.DetailFiveDayWeatherMapperImpl
import com.daylightapp.data.mapper.FiveDayWeatherMapperImpl
import com.daylightapp.data.mapper.ListMapper
import com.daylightapp.data.mapper.Mapper
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @[Binds Singleton]
    abstract fun bindFiveDayWeatherMapper(fiveDayWeatherMapperImpl: FiveDayWeatherMapperImpl): ListMapper<Response, FiveDayWeatherEntity>

    @[Binds Singleton]
    abstract fun bindDetailFiveDayWeatherMapper(detailFiveDayWeatherMapperImpl: DetailFiveDayWeatherMapperImpl): ListMapper<Response, DetailFiveDayWeatherEntity>

    @[Binds Singleton]
    abstract fun bindCityListMapper(cityListMapperImpl: CityListMapperImpl): ListMapper<City, LocationEntity>

    @[Binds Singleton]
    abstract fun bindCurrentWeatherMapper(currentWeatherMapperImpl: CurrentWeatherMapperImpl): Mapper<CurrentWeather, CurrentWeatherEntity>
}