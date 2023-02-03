package com.daylightapp.domain.usecase.quote

import app.cash.turbine.test
import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.TestResponseEnum
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class QuoteUseCaseImplTest {
    @InjectMocks
    private lateinit var quoteUseCase :FakeQuoteUseCase

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun when_quoteUseCase_is_success_response() = runBlocking(Dispatchers.Unconfined) {
        quoteUseCase.setRequest(TestResponseEnum.SUCCESS)
        quoteUseCase.invoke().test {
            Truth.assertThat(awaitItem()).isInstanceOf(NetworkResult.Success::class.java)
            awaitComplete()
        }
    }
}