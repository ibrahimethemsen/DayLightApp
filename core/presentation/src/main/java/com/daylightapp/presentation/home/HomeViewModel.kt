package com.daylightapp.presentation.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.domain.entity.quote.QuoteEntity
import com.daylightapp.domain.entity.weather.CurrentWeatherEntity
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.domain.usecase.datastore.read.CityDataStoreUseCase
import com.daylightapp.domain.usecase.quote.QuoteUseCase
import com.daylightapp.domain.usecase.weather.CurrentDayWeatherUseCase
import com.daylightapp.domain.usecase.weather.FiveDayWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val threeHoursWeatherForecastUseCase: FiveDayWeatherForecastUseCase,
    private val currentDayWeatherUseCase: CurrentDayWeatherUseCase,
    private val quoteUseCase: QuoteUseCase,
    readDataStoreUseCase: CityDataStoreUseCase
) : ViewModel() {
    private val _homeUiState = MutableLiveData<CurrentWeatherUiState>()
    val homeUiState: LiveData<CurrentWeatherUiState> = _homeUiState

    private val _quoteUiState = MutableLiveData<QuoteUiState>()
    val quoteUiState: LiveData<QuoteUiState> = _quoteUiState

    private val _fiveDayWeather = MutableLiveData<FiveDayWeatherUiState>()
    val fiveDayWeather: LiveData<FiveDayWeatherUiState> = _fiveDayWeather

    private val _locationLatLon = MutableLiveData<LocationEntity>()
    val locationLatLon : LiveData<LocationEntity> =_locationLatLon

    private val getLocation = readDataStoreUseCase.readCityDataStore

    init {
        getCurrentWeather()
        getQuote()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            getLocation.collectLatest {
                currentDayWeather(it.lat, it.lon)
                getFiveDayWeather(it.lat, it.lon)
                _locationLatLon.postValue(it)
            }
        }
    }

    private fun getQuote() {
        viewModelScope.launch {
            quoteUseCase().collect {
                when (it) {
                    is NetworkResult.Error -> {
                        _quoteUiState.postValue(
                            QuoteUiState().copy(
                                error = it.exception.localizedMessage,
                                loading = false
                            )
                        )
                        println("post ${it.exception}")
                    }
                    NetworkResult.Loading -> {
                        _quoteUiState.postValue(
                            QuoteUiState().copy(
                                loading = true
                            )
                        )
                        println("post loading")

                    }
                    is NetworkResult.Success -> {
                        _quoteUiState.postValue(
                            QuoteUiState().copy(
                                quoteEntity = it.data,
                                loading = false
                            )
                        )
                        println("post ${it.data}")
                    }
                }
            }
        }
    }

    private fun currentDayWeather(lat: String, lon: String) {
        viewModelScope.launch {
            currentDayWeatherUseCase(lat, lon).collect {
                when (it) {
                    is NetworkResult.Error -> {
                        _homeUiState.postValue(
                            CurrentWeatherUiState().copy(
                                error = it.exception.localizedMessage,
                                loading = false
                            )
                        )
                    }
                    NetworkResult.Loading -> {
                        _homeUiState.postValue(
                            CurrentWeatherUiState().copy(
                                loading = true
                            )
                        )
                    }
                    is NetworkResult.Success -> {
                        _homeUiState.postValue(
                            CurrentWeatherUiState().copy(
                                currentWeatherEntity = it.data,
                                loading = false
                            )
                        )

                    }
                }
            }
        }
    }

    private fun getFiveDayWeather(lat: String, lon: String) {
        viewModelScope.launch {
            threeHoursWeatherForecastUseCase(lat, lon).collect {
                when (it) {
                    is NetworkResult.Error -> {
                        _fiveDayWeather.postValue(
                            FiveDayWeatherUiState().copy(
                                error = it.exception.localizedMessage,
                                loading = false
                            )
                        )
                    }

                    NetworkResult.Loading -> {
                        _fiveDayWeather.postValue(
                            FiveDayWeatherUiState().copy(
                                loading = true
                            )
                        )
                    }

                    is NetworkResult.Success -> {
                        _fiveDayWeather.postValue(
                            FiveDayWeatherUiState().copy(
                                fiveDayWeather = it.data,
                                loading = false
                            )
                        )

                    }
                }
            }
        }
    }
}

data class CurrentWeatherUiState(
    val currentWeatherEntity: CurrentWeatherEntity? = null,
    val error: String? = null,
    val loading: Boolean = false
)

data class QuoteUiState(
    val quoteEntity: QuoteEntity? = null,
    val error: String? = null,
    val loading: Boolean = false
)

data class FiveDayWeatherUiState(
    val fiveDayWeather: List<FiveDayWeatherEntity>? = emptyList(),
    val error: String? = null,
    val loading: Boolean = false
)



