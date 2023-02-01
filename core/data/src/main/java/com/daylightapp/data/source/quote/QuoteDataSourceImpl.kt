package com.daylightapp.data.source.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.dto.quote.QuoteResponse
import com.daylightapp.data.api.quote.QuoteApi
import javax.inject.Inject

class QuoteDataSourceImpl @Inject constructor(
    private val quoteApi: QuoteApi
) : QuoteDataSource {
    override suspend fun getQuote(): NetworkResult<QuoteResponse> =
        try {
            NetworkResult.Success(quoteApi.getRandomQuote())
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }

}