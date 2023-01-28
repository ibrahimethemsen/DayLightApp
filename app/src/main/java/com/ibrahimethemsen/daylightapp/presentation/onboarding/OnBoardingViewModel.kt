package com.ibrahimethemsen.daylightapp.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.domain.usecase.city.GetListCityUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.write.WriteCityDataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getListCityUseCase: GetListCityUseCase,
    private val writeCityDataStoreUseCase: WriteCityDataStoreUseCase,
    private val readDataStoreUseCase: CityDataStoreUseCase
    ) : ViewModel() {
    private var _cityList = MutableLiveData<OnBoardingUi>()
    val cityList: LiveData<OnBoardingUi> = _cityList

    val getLocation = readDataStoreUseCase.readCityDataStore
    fun getList() {
        viewModelScope.launch {
            getListCityUseCase().collect {
                when (it) {
                    is NetworkResult.Error -> {
                        _cityList.postValue(
                            OnBoardingUi().copy(
                                error = it.exception,
                                loading = false
                            )
                        )
                    }

                    NetworkResult.Loading -> {
                        _cityList.postValue(
                            OnBoardingUi()
                        )
                    }

                    is NetworkResult.Success -> {
                        _cityList.postValue(
                            OnBoardingUi().copy(
                                data = it.data,
                                loading = false
                            )
                        )
                    }
                }
            }
        }
    }

    //TODO search iki ekranda olacagi icin TextWatcher'a tasinacak
    fun filterCityList(query: String?): List<City> {
        val searchList = mutableListOf<City>()
        query?.let {
            cityList.value?.data?.forEach { city ->
                if (city.name!!.lowercase().contains(it)) {
                    searchList.add(city)
                }
            }
        }
        return searchList
    }

    fun writeDataStoreCity(lat: String, lon: String,name : String) {
        viewModelScope.launch {
            writeCityDataStoreUseCase(lat, lon,name)
            println("latw ${lat}")
            println("latw ${lon}")
            println("latw ${name}")

        }
    }
}

data class OnBoardingUi(
    val data: List<City>? = null,
    val loading: Boolean = true,
    val error: Exception? = null
)

