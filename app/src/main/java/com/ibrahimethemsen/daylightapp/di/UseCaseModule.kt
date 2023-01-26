package com.ibrahimethemsen.daylightapp.di

import com.ibrahimethemsen.daylightapp.domain.usecase.city.GetListCityUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.city.GetListCityUseCaseImpl
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.ReadDataStoreUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.ReadDataStoreUseCaseImpl
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write.WriteDataStoreUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write.WriteDataStoreUseCaseImpl
import com.ibrahimethemsen.daylightapp.domain.usecase.weather.FiveDayWeatherForecastUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.weather.FiveDayWeatherForecastUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class UseCaseModule {
    @[Binds Singleton]
    abstract fun bindGetListUseCase(getListCityUseCaseImpl: GetListCityUseCaseImpl) : GetListCityUseCase

    @[Binds Singleton]
    abstract fun bindReadDataStoreUseCase(readDataStoreUseCaseImpl: ReadDataStoreUseCaseImpl) : ReadDataStoreUseCase

    @[Binds Singleton]
    abstract fun bindWriteDataStoreUseCase(writeDataStoreUseCaseImpl: WriteDataStoreUseCaseImpl) : WriteDataStoreUseCase

    @[Binds Singleton]
    abstract fun bindFiveDayWeatherUseCase(fiveDayWeatherForecastUseCaseImpl: FiveDayWeatherForecastUseCaseImpl) : FiveDayWeatherForecastUseCase
}