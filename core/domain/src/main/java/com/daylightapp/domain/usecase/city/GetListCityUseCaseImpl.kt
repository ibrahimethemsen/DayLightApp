package com.daylightapp.domain.usecase.city

import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCityUseCaseImpl @Inject constructor(
    private val cityRepository : CityRepository
) : GetListCityUseCase {
    override fun invoke(): Flow<NetworkResult<List<LocationEntity>>> = cityRepository.getAllCity()

}
