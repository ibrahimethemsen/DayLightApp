package com.ibrahimethemsen.daylightapp.di

import com.ibrahimethemsen.daylightapp.domain.usecase.city.GetListCityUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.city.GetListCityUseCaseImpl
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCaseImpl
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.NavStartDestinationUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.NavStartDestinationUseCaseImpl
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write.WriteCityDataStoreUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write.WriteCityDataStoreUseCaseImpl
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
    abstract fun bindReadDataStoreUseCase(readCityDataStoreUseCaseImpl: CityDataStoreUseCaseImpl) : CityDataStoreUseCase

    @[Binds Singleton]
    abstract fun bindWriteDataStoreUseCase(writeDataStoreUseCaseImpl: WriteCityDataStoreUseCaseImpl) : WriteCityDataStoreUseCase

    @[Binds Singleton]
    abstract fun bindFiveDayWeatherUseCase(fiveDayWeatherForecastUseCaseImpl: FiveDayWeatherForecastUseCaseImpl) : FiveDayWeatherForecastUseCase

    @[Binds Singleton]
    abstract fun bindReadNavStartDestinationUseCase(navStartDestinationUseCaseImpl: NavStartDestinationUseCaseImpl) : NavStartDestinationUseCase
}