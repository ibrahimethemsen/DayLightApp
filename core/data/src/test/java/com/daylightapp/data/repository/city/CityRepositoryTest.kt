package com.daylightapp.data.repository.city

import app.cash.turbine.test
import com.daylightapp.common.NetworkResult
import com.daylightapp.data.TestResponseEnum
import com.daylightapp.data.mapper.CityListMapperImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CityRepositoryTest {
    @Mock
    private lateinit var cityListMapper : CityListMapperImpl
    private lateinit var cityFakeRepository : FakeCityRepositoryImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        cityFakeRepository = FakeCityRepositoryImpl(cityListMapper)
    }

    @Test
    fun when_cityRepository_is_loading() = runBlocking{
        cityFakeRepository.getAllCity().test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            //Cancel this ReceiveTurbine
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun when_cityRepository_is_success() = runBlocking {
        cityFakeRepository.setRequest(TestResponseEnum.SUCCESS)
        cityFakeRepository.getAllCity().test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun when_cityRepository_is_error() = runBlocking {
        cityFakeRepository.setRequest(TestResponseEnum.ERROR)
        cityFakeRepository.getAllCity().test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Error::class.java)
            awaitComplete()
        }
    }
}