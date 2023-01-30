package com.daylightapp.data.di

import com.daylightapp.data.api.city.CityApi
import com.daylightapp.data.api.quote.QuoteApi
import com.daylightapp.data.api.weather.WeatherApi
import com.daylightapp.data.common.Constants.CITY_BASE_URL
import com.daylightapp.data.common.Constants.QUOTE_BASE_URL
import com.daylightapp.data.common.Constants.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {
    @[Provides Singleton]
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @[Provides Singleton]
    fun provideRetrofit(
        baseUrl: String,
        moshiConverterFactory: MoshiConverterFactory = provideMoshi()
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @[Provides Singleton]
    fun provideCityRetrofit(
    ): CityApi =
        provideRetrofit(CITY_BASE_URL).create(CityApi::class.java)

    @[Provides Singleton]
    fun provideWeatherRetrofit(): WeatherApi =
        provideRetrofit(WEATHER_BASE_URL).create(WeatherApi::class.java)

    @[Provides Singleton]
    fun provideQuoteRetrofit(): QuoteApi =
        provideRetrofit(QUOTE_BASE_URL).create(QuoteApi::class.java)
}