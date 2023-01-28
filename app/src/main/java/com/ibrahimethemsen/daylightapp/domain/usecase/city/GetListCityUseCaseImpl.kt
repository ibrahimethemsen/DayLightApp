package com.ibrahimethemsen.daylightapp.domain.usecase.city

import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCityUseCaseImpl @Inject constructor(
    private val cityRepository : CityRepository
) : GetListCityUseCase {
    override fun invoke(): Flow<NetworkResult<List<City>>> = cityRepository.getAllCity()

}
