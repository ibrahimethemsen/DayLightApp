package com.ibrahimethemsen.daylightapp.di

import com.ibrahimethemsen.daylightapp.common.Constants.CITY_BASE_URL
import com.ibrahimethemsen.daylightapp.data.api.CityApi
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
        moshiConverterFactory: MoshiConverterFactory
    ): CityApi {
        return Retrofit.Builder()
            .baseUrl(CITY_BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(CityApi::class.java)
    }
}