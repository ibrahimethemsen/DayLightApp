package com.ibrahimethemsen.daylightapp.di

import com.ibrahimethemsen.daylightapp.data.repository.city.CityRepositoryImpl
import com.ibrahimethemsen.daylightapp.domain.repository.city.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {
    @[Binds Singleton]
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl) : CityRepository
}