package com.daylightapp.data.api.quote

import com.daylightapp.data.dto.quote.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query
const val MIN_LENGTH = 150
const val MAX_LENGTH = 300
interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote(
        @Query("minLength") minLenght : Int = MIN_LENGTH,
        @Query("maxLength") maxLength : Int = MAX_LENGTH,
    ) : QuoteResponse
}