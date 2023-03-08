package com.daylightapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val remoteConfig : FirebaseRemoteConfig
) : ViewModel() {
    private val _registerScreenABTest = MutableLiveData<String>()
    val registerScreenABTest : LiveData<String> = _registerScreenABTest

    private val _welcomeBanner = MutableLiveData<WelcomeBanner>()
    val welcomeBanner : LiveData<WelcomeBanner> = _welcomeBanner

    private val _bannerListener = MutableLiveData<BannerData>()
    val bannerListener : LiveData<BannerData> = _bannerListener
    init {
        setRemoteConfig()
    }

    fun bannerListener(){
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                val banner = remoteConfig.getString("home_banner")
                val gson = Gson()
                val jsonModel = gson.fromJson(banner,BannerData::class.java)
                println("jsonModel $jsonModel")
                _bannerListener.postValue(jsonModel)
            }
        }
    }

    private fun setRemoteConfig(){
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                //int boolean string-json
                _registerScreenABTest.postValue(remoteConfig.getString("register_test"))

                val banner = remoteConfig.getString("welcome_banner")
                val gson = Gson()
                val jsonModel = gson.fromJson(banner,WelcomeBanner::class.java)
                _welcomeBanner.postValue(jsonModel)
            }
        }
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