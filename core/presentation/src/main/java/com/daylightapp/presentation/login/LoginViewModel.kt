package com.daylightapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daylightapp.common.NetworkResult
import com.daylightapp.presentation.common.fetchToLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val remoteConfig : FirebaseRemoteConfig,
    private val gson: Gson,
    private val firebaseAuth : FirebaseAuth
) : ViewModel() {
    private val _registerScreenABTest = MutableLiveData<String>()
    val registerScreenABTest : LiveData<String> = _registerScreenABTest

    private val _welcomeBanner = MutableLiveData<WelcomeBanner>()
    val welcomeBanner : LiveData<WelcomeBanner> = _welcomeBanner

    private val _bannerListener = MutableLiveData<BannerData>()
    val bannerListener : LiveData<BannerData> = _bannerListener
    init {
        setABTestRegister()
    }

    fun bannerListener(){
        remoteConfig.fetchToLiveData("home_banner",gson,_bannerListener)
    }

    private fun setABTestRegister(){
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                _registerScreenABTest.postValue(remoteConfig.getString("register_test"))
            }
        }
    }

    fun welcomeBannerListener(){
        remoteConfig.fetchToLiveData("welcome_banner",gson,_welcomeBanner)
    }

    fun loginAccount(email: String, password: String): Flow<NetworkResult<Boolean>> {
        return callbackFlow {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        trySend(NetworkResult.Success(true))
                    }.addOnFailureListener {
                        trySend(NetworkResult.Error(it))
                    }
            }catch (e : Exception){
                trySend(NetworkResult.Error(e))
            }
            awaitClose()
        }
    }
    fun isEmpty(
        email: String?,
        password: String?
    ): Boolean{
        return !email.isNullOrBlank() && !password.isNullOrBlank()
    }
}

data class WelcomeBanner(
    @SerializedName("welcome_visibility")
    val welcomeVisibility : Boolean = false,
    @SerializedName("welcome_url")
    val welcomeUrl : String,
    @SerializedName("welcome_description")
    val welcomeDescription : String
)

data class BannerData(
    @SerializedName("visibility")
    val visibility : Boolean = false,
    @SerializedName("banner_text")
    val bannerText : String,
    @SerializedName("banner_season")
    val bannerSeason : String
)