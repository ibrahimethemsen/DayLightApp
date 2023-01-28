package com.ibrahimethemsen.daylightapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
object DispatchersModule {
    @IoDispatcher
    @Provides
    fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher