package com.daylightapp.domain.repository.repository

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.quote.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getQuote() : Flow<NetworkResult<QuoteEntity>>
}