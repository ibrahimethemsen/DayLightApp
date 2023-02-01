package com.daylightapp.data.source.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.dto.quote.QuoteResponse

interface QuoteDataSource {
    suspend fun getQuote() : NetworkResult<QuoteResponse>
}