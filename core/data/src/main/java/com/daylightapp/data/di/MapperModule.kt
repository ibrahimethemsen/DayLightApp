package com.daylightapp.data.di

import com.daylightapp.common.weather.fiveday.Response
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.domain.mapper.DetailFiveDayWeatherMapperImpl
import com.daylightapp.domain.mapper.FiveDayWeatherMapperImpl
import com.daylightapp.domain.mapper.ListMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @[Binds Singleton]
    abstract fun bindFiveDayWeatherMapper(fiveDayWeatherMapperImpl: FiveDayWeatherMapperImpl) : ListMapper<Response,FiveDayWeatherEntity>

    @[Binds Singleton]
    abstract fun bindDetailFiveDayWeatherMapper(detailFiveDayWeatherMapperImpl: DetailFiveDayWeatherMapperImpl) : ListMapper<Response,DetailFiveDayWeatherEntity>
}