package com.daylightapp.domain.usecase.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.quote.QuoteEntity
import com.daylightapp.domain.repository.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteUseCaseImpl @Inject constructor(
    private val quoteRepository : QuoteRepository
): QuoteUseCase {
    override fun invoke(): Flow<NetworkResult<QuoteEntity>> = quoteRepository.getQuote()
}