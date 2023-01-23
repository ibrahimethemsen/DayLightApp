package com.ibrahimethemsen.daylightapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.domain.usecase.GetListCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getListCityUseCase: GetListCityUseCase
): ViewModel() {
    fun getList(){
        viewModelScope.launch {
            getListCityUseCase().collect{
                when(it){
                    is NetworkResult.Error -> {
                        println(it.exception)
                    }
                    NetworkResult.Loading -> {
                        println("loading")
                    }
                    is NetworkResult.Success -> {
                        println(it.data)
                    }
                }
            }
        }
    }
}