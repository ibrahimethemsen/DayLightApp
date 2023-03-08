package com.daylightapp.presentation.di

import android.os.Bundle
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]

object UtilityModule {
    @[Provides Singleton]
    fun provideGson() : Gson {
        return Gson()
    }
}