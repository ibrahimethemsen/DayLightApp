package com.daylightapp.domain.usecase.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.TestResponseEnum
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.testCityEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCityUseCaseImpl : GetListCityUseCase {
    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest: TestResponseEnum) {
        this.testRequest = testRequest
    }
    override fun invoke(): Flow<NetworkResult<List<LocationEntity>>> = flow{
        when (testRequest) {
            TestResponseEnum.ERROR -> emit(NetworkResult.Error(Exception("Bir hata oluştu")))
            TestResponseEnum.SUCCESS -> emit(NetworkResult.Success(testCityEntityList))
            TestResponseEnum.LOADING -> emit(NetworkResult.Loading)
        }
    }
}