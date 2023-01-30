package com.daylightapp.domain.usecase.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.quote.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteUseCase {
    operator fun invoke() : Flow<NetworkResult<QuoteEntity>>
}