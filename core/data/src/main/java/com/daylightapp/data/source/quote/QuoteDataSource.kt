package com.daylightapp.data.source.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.quote.QuoteResponse

interface QuoteDataSource {
    suspend fun getQuote() : NetworkResult<QuoteResponse>
}