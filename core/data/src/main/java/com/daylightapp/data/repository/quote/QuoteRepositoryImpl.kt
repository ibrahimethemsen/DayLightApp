package com.daylightapp.data.repository.quote

import androidx.lifecycle.LiveData
import com.daylightapp.common.NetworkResult
import com.daylightapp.common.datastore.LocalQuote
import com.daylightapp.data.source.datastore.DataStoreDataSource
import com.daylightapp.data.source.quote.QuoteDataSource
import com.daylightapp.domain.entity.quote.QuoteEntity
import com.daylightapp.domain.repository.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

class QuoteRepositoryImpl @Inject constructor(
    private val quoteDataSource: QuoteDataSource,
    private val dataStoreSource : DataStoreDataSource
) : QuoteRepository {

    val currentDate : LiveData<LocalQuote> = dataStoreSource.readCurrentDate.collectLatest {

    }
    override fun getQuote(): Flow<NetworkResult<QuoteEntity>> = flow<NetworkResult<QuoteEntity>> {
        emit(NetworkResult.Loading)
    }.catch {
        emit(NetworkResult.Error(it))
    }.map {
        val currentDate = Calendar.getInstance().time
        val formatDate = SimpleDateFormat(DAY_MONTH_FORMAT, Locale("tr")).format(currentDate)
        dataStoreSource.readCurrentDate.collectLatest {

        }

    }
    companion object {
        const val DAY_MONTH_FORMAT = "dd-MMM"
    }
}