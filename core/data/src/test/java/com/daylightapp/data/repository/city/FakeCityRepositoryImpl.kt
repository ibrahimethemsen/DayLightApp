package com.daylightapp.data.repository.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.TestResponseEnum
import com.daylightapp.data.mapper.CityListMapperImpl
import com.daylightapp.data.testCityList
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCityRepositoryImpl(
    private val mapper: CityListMapperImpl
) : CityRepository {
    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest: TestResponseEnum) {
        this.testRequest = testRequest
    }

    override fun getAllCity(): Flow<NetworkResult<List<LocationEntity>>> = flow {
        emit(NetworkResult.Loading)
        when (testRequest) {
            TestResponseEnum.ERROR -> emit(NetworkResult.Error(Exception("Bir hata oluştu")))
            TestResponseEnum.SUCCESS -> emit(NetworkResult.Success(mapper.map(testCityList)))
            TestResponseEnum.LOADING -> emit(NetworkResult.Loading)

        }

    }
}