package com.daylightapp.presentation.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCase
import com.daylightapp.domain.usecase.weather.FiveDayWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fiveDayWeatherForecastUseCase: FiveDayWeatherForecastUseCase,
    private val readDataStoreUseCase: CityDataStoreUseCase
) : ViewModel() {
    private val _homeUiState = MutableLiveData<String>()
    val homeUiState : LiveData<String> = _homeUiState

    private val getLocation = readDataStoreUseCase.readCityDataStore

    fun getFiveWeather() {
        viewModelScope.launch {
            getLocation.collectLatest {
                getWeather(it.lat,it.lon)
            }
        }
    }

    private fun getWeather(lat: String, lon: String) {
        viewModelScope.launch {
            fiveDayWeatherForecastUseCase(lat, lon).collect {
                when (it) {
                    is NetworkResult.Error -> {
                        println("error ")
                        println("error ${it.exception}")
                    }

                    NetworkResult.Loading -> {
                        println("loading ")
                    }

                    is NetworkResult.Success -> {
                        println("success ")
                        println("success ${it.data.city} ")
                        it.data.city?.name?.let {
                            _homeUiState.postValue(it)
                        }
                    }
                }
            }
        }
    }
}