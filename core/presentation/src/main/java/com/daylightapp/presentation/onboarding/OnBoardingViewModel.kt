package com.daylightapp.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daylightapp.common.NetworkResult
import com.daylightapp.common.city.City
import com.daylightapp.domain.usecase.city.GetListCityUseCase
import com.daylightapp.domain.usecase.datastore.read.NavStartDestinationUseCase
import com.daylightapp.domain.usecase.datastore.write.WriteCityDataStoreUseCase
import com.daylightapp.domain.usecase.datastore.write.WriteNavStartDestinationUseCase
import com.daylightapp.presentation.common.Constants.HOME_FRAGMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getListCityUseCase: GetListCityUseCase,
    private val writeCityDataStoreUseCase: WriteCityDataStoreUseCase,
    private val writeNavStartDestination: WriteNavStartDestinationUseCase,
    private val readNavStartDestination: NavStartDestinationUseCase,
) : ViewModel() {
    private var _cityList = MutableLiveData<OnBoardingUi>()
    val cityList: LiveData<OnBoardingUi> = _cityList

    private var _navStartDestination = MutableLiveData<String>()
    val navStartDestination: LiveData<String> = _navStartDestination

    private val readNavStart = readNavStartDestination.readNavStartDestination

     fun getStartDestination() {
        viewModelScope.launch {
            readNavStart.collect {
                _navStartDestination.postValue(it)
            }
        }
    }

     fun getList() {
        viewModelScope.launch {
            getListCityUseCase().collect{
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
                        _cityList.postValue (
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

    fun writeDataStoreCity(lat: String, lon: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            writeCityDataStoreUseCase(lat, lon, name)
            writeNavStartDestination(HOME_FRAGMENT)
        }
    }
}

data class OnBoardingUi(
    val data: List<City>? = listOf(),
    val loading: Boolean = true,
    val error: Throwable? = null
)

