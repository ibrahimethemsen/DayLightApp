package com.daylightapp.domain.usecase.city

import app.cash.turbine.test
import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.TestResponseEnum
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class CityUseCaseImplTest {
    @InjectMocks
    private lateinit var cityUseCase: FakeCityUseCaseImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun when_cityUseCase_is_success_response() = runBlocking(Dispatchers.Unconfined) {
        cityUseCase.setRequest(TestResponseEnum.SUCCESS)
        cityUseCase.invoke().test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun when_cityUseCase_is_error_response() = runBlocking(Dispatchers.Unconfined) {
        cityUseCase.setRequest(TestResponseEnum.ERROR)
        cityUseCase.invoke().test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Error::class.java)
            awaitComplete()
        }
    }
}