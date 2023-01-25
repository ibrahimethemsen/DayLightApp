package com.ibrahimethemsen.daylightapp.presentation.onboarding

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.daylightapp.data.NetworkResult
import com.ibrahimethemsen.daylightapp.data.dto.City
import com.ibrahimethemsen.daylightapp.domain.usecase.GetListCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getListCityUseCase: GetListCityUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private var _cityList = MutableLiveData<OnBoardingUi>()
    val cityList: LiveData<OnBoardingUi> = _cityList

    private object PreferencesKeys {
        val lon = stringPreferencesKey("lon")
        val lan = stringPreferencesKey("lan")
    }


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

    fun addDataStoreCity(lat: String, lon: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey("lan")] = lat
                preferences[stringPreferencesKey("lon")] = lon
                println("edit")
            }
        }
    }



    val readFromDataStore: Flow<LocationEntity> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            mapPreferencesKey(preference)
        }


    private fun mapPreferencesKey(preferences: Preferences): LocationEntity {
        val lon = preferences[PreferencesKeys.lon] ?: "d0"
        val lan = preferences[PreferencesKeys.lan] ?: "d1"
        return LocationEntity(lon, lan)
    }

}

data class OnBoardingUi(
    val data: List<City>? = null,
    val loading: Boolean = true,
    val error: Exception? = null
)

data class LocationEntity(
    val lon: String,
    val lan: String
)