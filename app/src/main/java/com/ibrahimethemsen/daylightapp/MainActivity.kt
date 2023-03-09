package com.ibrahimethemsen.daylightapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.ibrahimethemsen.daylightapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var remoteConfig : FirebaseRemoteConfig

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isMaintenance()
    }

    private fun isMaintenance(){
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                if (remoteConfig.getBoolean("is_maintenance")){
                    setVisibility(View.GONE,View.VISIBLE)
                }else{
                    setVisibility(View.VISIBLE,View.GONE)
                }
            }
        }
    }
    private fun setVisibility(navHost : Int,maintenance : Int){
        binding.apply {
            fragmentContainerView.visibility = navHost
            mainMaintenanceGroup.visibility = maintenance
            mainProgress.visibility = View.GONE
        }
    }
}