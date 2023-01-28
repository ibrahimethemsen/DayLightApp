package com.daylightapp.domain.usecase.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.common.city.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCityUseCaseImpl @Inject constructor(
    private val cityRepository : com.daylightapp.domain.repository.city.CityRepository
) : GetListCityUseCase {
    override fun invoke(): Flow<NetworkResult<List<City>>> = cityRepository.getAllCity()

}
