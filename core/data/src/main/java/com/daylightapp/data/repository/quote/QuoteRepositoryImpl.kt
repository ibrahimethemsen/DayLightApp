package com.daylightapp.data.repository.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.common.Constants.DAY_MONTH_FORMAT
import com.daylightapp.data.source.datastore.DataStoreDataSource
import com.daylightapp.data.source.quote.QuoteDataSource
import com.daylightapp.domain.common.currentDateFormat
import com.daylightapp.domain.entity.quote.QuoteEntity
import com.daylightapp.domain.repository.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteDataSource: QuoteDataSource,
    private val dataStoreSource: DataStoreDataSource
) : QuoteRepository {

    override fun getQuote(): Flow<NetworkResult<QuoteEntity>> = flow {
        emit(NetworkResult.Loading)
        val currentDate = DAY_MONTH_FORMAT.currentDateFormat()
        dataStoreSource.readCurrentDate.collect {
            if (it == currentDate) {
                dataStoreSource.readLocalQuote.collect { quote ->
                    emit(NetworkResult.Success(quote))
                }
            } else {
                when (val response = quoteDataSource.getQuote()) {
                    is NetworkResult.Error -> {
                        emit(NetworkResult.Error(response.exception))
                    }
                    NetworkResult.Loading -> {
                        emit(NetworkResult.Loading)
                    }
                    is NetworkResult.Success -> {
                        if (!response.data.content.isNullOrEmpty() && !response.data.author.isNullOrEmpty()) {
                            dataStoreSource.writeLocalQuote(
                                response.data.content!!,
                                response.data.author!!
                            )
                            dataStoreSource.writeCurrentDate(currentDate)
                        }
                    }
                }
            }
        }
    }
}