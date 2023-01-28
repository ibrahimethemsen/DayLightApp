package com.daylightapp.data.di

import com.daylightapp.data.repository.city.CityRepositoryImpl
import com.daylightapp.data.repository.datastore.DataStoreRepositoryImpl
import com.daylightapp.data.repository.weather.WeatherRepositoryImpl
import com.daylightapp.domain.repository.city.CityRepository
import com.daylightapp.domain.repository.datastore.DataStoreRepository
import com.daylightapp.domain.repository.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {
    @[Binds Singleton]
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl) : CityRepository

    @[Binds Singleton]
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl) : DataStoreRepository

    @[Binds Singleton]
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository
}