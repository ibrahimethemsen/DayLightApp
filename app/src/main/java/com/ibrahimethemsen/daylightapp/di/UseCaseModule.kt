package com.ibrahimethemsen.daylightapp.di

import com.ibrahimethemsen.daylightapp.domain.usecase.GetListCityUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.GetListCityUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class UseCaseModule {
    @[Binds Singleton]
    abstract fun bindGetListUseCase(getListCityUseCaseImpl: GetListCityUseCaseImpl) : GetListCityUseCase
}