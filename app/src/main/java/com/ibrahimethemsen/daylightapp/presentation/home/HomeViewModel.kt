package com.ibrahimethemsen.daylightapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.domain.usecase.datastore.read.ReadDataStoreUseCase
import com.ibrahimethemsen.daylightapp.domain.usecase.weather.FiveDayWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fiveDayWeatherForecastUseCase: FiveDayWeatherForecastUseCase,
    private val readDataStoreUseCase: ReadDataStoreUseCase
) : ViewModel() {
    private val _homeUiState = MutableLiveData<String>()
    val homeUiState : LiveData<String> = _homeUiState

    private val getLocation = readDataStoreUseCase.readCityDataStore

    fun getFiveWeather() {
        viewModelScope.launch {
            getLocation.onEach {
                getWeather(it.lat,it.lon)
            }.launchIn(this)
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