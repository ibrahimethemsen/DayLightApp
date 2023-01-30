package com.daylightapp.data.di

import com.daylightapp.data.source.city.CityDataSource
import com.daylightapp.data.source.city.CityDataSourceImpl
import com.daylightapp.data.source.datastore.DataStoreDataSource
import com.daylightapp.data.source.datastore.DataStoreDataSourceImpl
import com.daylightapp.data.source.quote.QuoteDataSource
import com.daylightapp.data.source.quote.QuoteDataSourceImpl
import com.daylightapp.data.source.weather.WeatherDataSource
import com.daylightapp.data.source.weather.WeatherDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {
    //Interface'in inject edilmesi için
    @[Binds Singleton]
    abstract fun bindCityDataSource(cityDataSourceImpl: CityDataSourceImpl) : CityDataSource

    @[Binds Singleton]
    abstract fun bindDataStoreDataSource(dataStoreDataSourceImpl: DataStoreDataSourceImpl) : DataStoreDataSource

    @[Binds Singleton]
    abstract fun bindWeatherDataSource(weatherDataSourceImpl: WeatherDataSourceImpl) : WeatherDataSource

    @[Binds Singleton]
    abstract fun bindQuoteDataSource(quoteDataSourceImpl: QuoteDataSourceImpl) : QuoteDataSource
}