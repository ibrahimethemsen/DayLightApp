package com.ibrahimethemsen.daylightapp.di

import com.ibrahimethemsen.daylightapp.common.Constants.CITY_BASE_URL
import com.ibrahimethemsen.daylightapp.common.Constants.WEATHER_BASE_URL
import com.ibrahimethemsen.daylightapp.data.api.city.CityApi
import com.ibrahimethemsen.daylightapp.data.api.weather.WeatherApi
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

}