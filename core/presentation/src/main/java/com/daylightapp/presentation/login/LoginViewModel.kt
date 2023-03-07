package com.daylightapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val remoteConfig : FirebaseRemoteConfig
) : ViewModel() {
    private val _registerScreenABTest = MutableLiveData<String>()
    val registerScreenABTest : LiveData<String> = _registerScreenABTest

    init {
        setRemoteConfig()
    }

    private fun setRemoteConfig(){
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                //int boolean string-json
                _registerScreenABTest.postValue(remoteConfig.getString("register_test"))
            }
        }
    }
}