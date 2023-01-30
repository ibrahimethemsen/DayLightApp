package com.daylightapp.data.repository.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.source.quote.QuoteDataSource
import com.daylightapp.domain.entity.quote.QuoteEntity
import com.daylightapp.domain.repository.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteDataSource: QuoteDataSource
) : QuoteRepository {
    override fun getQuote(): Flow<NetworkResult<QuoteEntity>> = flow<NetworkResult<QuoteEntity>> {
        NetworkResult.Loading
    }.catch {
        NetworkResult.Error(it)
    }.map {
        when(val response = quoteDataSource.getQuote()){
            is NetworkResult.Error -> {
                NetworkResult.Error(response.exception)
            }
            NetworkResult.Loading -> {
                NetworkResult.Loading
            }
            is NetworkResult.Success -> {
                val quoteEntity = QuoteEntity(response.data.content,response.data.author)
                NetworkResult.Success(quoteEntity)
            }
        }
    }
}