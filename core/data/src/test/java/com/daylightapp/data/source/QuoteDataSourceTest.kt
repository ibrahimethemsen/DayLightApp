package com.daylightapp.data.source

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.api.quote.MAX_LENGTH
import com.daylightapp.data.api.quote.MIN_LENGTH
import com.daylightapp.data.api.quote.QuoteApi
import com.daylightapp.data.testQuoteDto
import com.daylightapp.data.source.quote.QuoteDataSourceImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class QuoteDataSourceTest {
    @Mock
    private lateinit var quoteApi : QuoteApi
    private lateinit var quoteDataSource : QuoteDataSourceImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        quoteDataSource = QuoteDataSourceImpl(quoteApi)
    }

    @Test
    fun when_getQuote_is_success_response() = runBlocking{
        `when`(quoteApi.getRandomQuote(MIN_LENGTH, MAX_LENGTH)).thenReturn(
            testQuoteDto
        )
        val response = quoteDataSource.getQuote()
        assertThat(response).isInstanceOf(NetworkResult.Success::class.java)
    }

    @Test
    fun when_getQuote_is_error_response() = runBlocking {
        `when`(quoteApi.getRandomQuote(MIN_LENGTH, MAX_LENGTH)).thenReturn(
            null
        )
        val response = quoteDataSource.getQuote()
        assertThat(response).isInstanceOf(NetworkResult.Error::class.java)
    }
}