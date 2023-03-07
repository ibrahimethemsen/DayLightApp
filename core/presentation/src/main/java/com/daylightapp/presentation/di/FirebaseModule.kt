package com.daylightapp.presentation.di

import android.content.Context
import com.daylightapp.presentation.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object FirebaseModule {
    @[Provides Singleton]
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance().apply {
            val remoteConfigSettings = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(2).build()
            setConfigSettingsAsync(remoteConfigSettings)
            setDefaultsAsync(R.xml.network_security_config)
        }
    }

    @[Provides Singleton]
    fun provideAnalytics(
        @ApplicationContext context : Context
    ) : FirebaseAnalytics{
        return FirebaseAnalytics.getInstance(context)
    }


}