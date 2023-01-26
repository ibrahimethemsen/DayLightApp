package com.ibrahimethemsen.daylightapp.domain.usecase.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListCityUseCaseImpl @Inject constructor(
    private val cityRepository : CityRepository
) : GetListCityUseCase {
    override fun invoke(): Flow<NetworkResult<List<City>>> {
        return flow {
            emit(NetworkResult.Loading)
            when(val response = cityRepository.getAllCity()){
                is NetworkResult.Error -> emit(NetworkResult.Error(response.exception))
                NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> emit(NetworkResult.Success(response.data))
            }
        }
    }
}