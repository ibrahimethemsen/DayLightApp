package com.daylightapp.data.api.quote

import com.daylightapp.common.quote.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query
const val MIN_LENGTH = 100
const val MAX_LENGTH = 250
interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote(
        @Query("minLength") minLenght : Int = MIN_LENGTH,
        @Query("maxLength") maxLength : Int = MAX_LENGTH,
    ) : QuoteResponse
}