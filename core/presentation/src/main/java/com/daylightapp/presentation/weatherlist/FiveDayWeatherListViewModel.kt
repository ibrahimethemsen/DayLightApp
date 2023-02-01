package com.daylightapp.presentation.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.daylightapp.common.NetworkResult
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.domain.usecase.weather.DetailFiveDayWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiveDayWeatherListViewModel @Inject constructor(
    private val detailFiveDayWeatherUseCase : DetailFiveDayWeatherUseCase,
    savedStateHandle : SavedStateHandle
) : ViewModel() {
    private val _detailFiveUiState = MutableLiveData<DetailFiveUiState>()
    val detailFiveUiState : LiveData<DetailFiveUiState> = _detailFiveUiState

    init {
        val args = FiveDayWeatherListFragmentArgs.fromSavedStateHandle(savedStateHandle)
        getFiveDayWeatherUseCase(
            lat = args.lat,
            lon = args.lon
        )
    }

    private fun getFiveDayWeatherUseCase(lat : String,lon : String){
        viewModelScope.launch {
            detailFiveDayWeatherUseCase(lat, lon).collect{
                when(it){
                    is NetworkResult.Error -> {
                        _detailFiveUiState.postValue(
                            DetailFiveUiState().copy(
                                loading = false,
                                error = it.exception.localizedMessage
                            )
                        )
                    }
                    NetworkResult.Loading -> {
                        _detailFiveUiState.postValue(
                            DetailFiveUiState().copy(
                                loading = true
                            )
                        )
                    }
                    is NetworkResult.Success -> {
                        _detailFiveUiState.postValue(
                            DetailFiveUiState().copy(
                                loading = false,
                                data = it.data
                            )
                        )
                    }
                }
            }
        }
    }
}

data class DetailFiveUiState(
    val data : List<DetailFiveDayWeatherEntity> = emptyList(),
    val error : String? = null,
    val loading : Boolean = false
)