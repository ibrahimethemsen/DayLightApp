package com.daylightapp.domain.usecase.quote

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.TestResponseEnum
import com.daylightapp.domain.entity.quote.QuoteEntity
import com.daylightapp.domain.testQuoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeQuoteUseCase : QuoteUseCase {
    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest: TestResponseEnum) {
        this.testRequest = testRequest
    }
    override fun invoke(): Flow<NetworkResult<QuoteEntity>> = flow{
        when (testRequest) {
            TestResponseEnum.ERROR -> emit(NetworkResult.Error(Exception("Bir hata oluştu")))
            TestResponseEnum.SUCCESS -> emit(NetworkResult.Success(testQuoteEntity))
            TestResponseEnum.LOADING -> emit(NetworkResult.Loading)
        }
    }
}