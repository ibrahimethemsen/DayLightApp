package com.daylightapp.data.api

import com.daylightapp.data.Constants.FILE_NAME_RANDOM_QUOTE_RESPONSE
import com.daylightapp.data.Constants.MIN_LENGHT
import com.daylightapp.data.Constants.MAX_LENGHT
import com.daylightapp.data.api.quote.QuoteApi
import com.daylightapp.data.enqueueResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class QuoteApiTest {
    private lateinit var quoteApi: QuoteApi
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        quoteApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
        mockWebServer.enqueueResponse(FILE_NAME_RANDOM_QUOTE_RESPONSE)
    }

    @Test
    fun when_randomQuote_is_notNull() = runBlocking {
        val response = quoteApi.getRandomQuote(MIN_LENGHT, MAX_LENGHT)
        assertThat(response).isNotNull()
    }

    @Test
    fun when_randomQuote_is_quoteItem_expected() = runBlocking {
        val response = quoteApi.getRandomQuote(MIN_LENGHT, MAX_LENGHT)
        val author = response.author
        val content = response.content
        val lenght = response.length in MIN_LENGHT..MAX_LENGHT
        assertThat(author).isEqualTo("William Hurt")
        assertThat(content).isEqualTo("The problem with Google is you have 360 degrees of omnidirectional information on a linear basis, but the algorithms for irony and ambiguity are not there. And those are the algorithms of wisdom.")
        assertThat(lenght).isTrue()
    }

    @Test
    fun when_randomQuote_is_requestPath() = runBlocking {
        val response = quoteApi.getRandomQuote(MIN_LENGHT, MAX_LENGHT)
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/random?minLength=${MIN_LENGHT}&maxLength=${MAX_LENGHT}")
    }


    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }
}