package com.daylightapp.domain.di


import com.daylightapp.domain.usecase.city.GetListCityUseCase
import com.daylightapp.domain.usecase.city.GetListCityUseCaseImpl
import com.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCase
import com.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCaseImpl
import com.daylightapp.domain.usecase.datastore.read.NavStartDestinationUseCase
import com.daylightapp.domain.usecase.datastore.read.NavStartDestinationUseCaseImpl
import com.daylightapp.domain.usecase.datastore.write.WriteCityDataStoreUseCase
import com.daylightapp.domain.usecase.datastore.write.WriteCityDataStoreUseCaseImpl
import com.daylightapp.domain.usecase.datastore.write.WriteNavStartDestinationUseCase
import com.daylightapp.domain.usecase.datastore.write.WriteNavStartDestinationUseCaseImpl
import com.daylightapp.domain.usecase.quote.QuoteUseCase
import com.daylightapp.domain.usecase.quote.QuoteUseCaseImpl
import com.daylightapp.domain.usecase.weather.*
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
    abstract fun bindReadNavStartDestinationUseCase(navStartDestinationUseCaseImpl: NavStartDestinationUseCaseImpl) : NavStartDestinationUseCase

    @[Binds Singleton]
    abstract fun bindWriteNavStartDestinationUseCase(writeNavStartDestinationUseCaseImpl: WriteNavStartDestinationUseCaseImpl) : WriteNavStartDestinationUseCase

    @[Binds Singleton]
    abstract fun bindFiveDayWeatherUseCase(fiveDayWeatherForecastUseCaseImpl: FiveDayWeatherForecastUseCaseImpl) : FiveDayWeatherForecastUseCase

    @[Binds Singleton]
    abstract fun bindCurrentDayWeatherUseCase(currentDayWeatherImpl: CurrentDayWeatherUseCaseImpl) : CurrentDayWeatherUseCase

    @[Binds Singleton]
    abstract fun bindDetailFiveDayWeatherUseCase(detailFiveDayWeatherUseCaseImpl: DetailFiveDayWeatherUseCaseImpl) : DetailFiveDayWeatherUseCase

    @[Binds Singleton]
    abstract fun bindQuoteUseCase(quoteUseCaseImpl: QuoteUseCaseImpl) : QuoteUseCase
}