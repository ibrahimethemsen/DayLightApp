package com.daylightapp.presentation.home


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.loadImage
import com.daylightapp.presentation.common.observeIfNotNull
import com.daylightapp.presentation.databinding.FragmentHomeBinding
import com.daylightapp.presentation.home.adapter.FiveDayWeatherAdapter
import com.daylightapp.presentation.home.adapter.SliderAdapter
import com.daylightapp.presentation.home.model.SliderModel
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBindingInflater(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val fiveDayAdapter = FiveDayWeatherAdapter()
    private val sliderAdapter = SliderAdapter()
    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observe()
        listener()
        viewModel.homeSliderRemoteConfig()
    }

    private fun initAdapter(){
        binding.homeFiveDayWeatherRv.adapter = fiveDayAdapter
        binding.homeSliderRv.adapter = sliderAdapter
    }


    private fun listener(){
        binding.apply {
            homeToLoginBtn.setOnClickListener {toLoginFragment()}
            sliderAdapter.sliderClickListener(::sliderIntent)
        }
    }

    private fun sliderIntent(intentUrl : String){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(intentUrl)
        )
        startActivity(urlIntent)
    }

    private fun toLoginFragment(){
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun observe() {
        observeIfNotNull(viewModel.homeUiState,::setHomeUiState)
        observeIfNotNull(viewModel.quoteUiState,::setQuoteUiState)
        observeIfNotNull(viewModel.fiveDayWeather,::setFiveDayWeatherUiState)
        observeIfNotNull(viewModel.locationLatLon,::navigateHomeToFiveDay)
        observeIfNotNull(viewModel.homeSlider,::setSlider)
    }

    private fun setSlider(listSlider : List<SliderModel>){
        sliderAdapter.updateRecyclerList(listSlider)
    }

    private fun setHomeUiState(currentWeather : CurrentWeatherUiState){
        binding.apply {
            homeSunriseTv.text = currentWeather.currentWeatherEntity?.sunrise
            homeSunsetTv.text = currentWeather.currentWeatherEntity?.sunset
            homeWindTv.text = currentWeather.currentWeatherEntity?.windSpeed
            homeCurrentCelciusTv.text = currentWeather.currentWeatherEntity?.currentCelcius
            homeCurrentDate.text = currentWeather.currentWeatherEntity?.currentDate
            currentWeather.currentWeatherEntity?.imageIconId?.let {
                homeCurrentIconIv.loadImage(it)
            }
            homeCurrentDescriptionTv.text = currentWeather.currentWeatherEntity?.description
        }
    }
    private fun setQuoteUiState(quote : QuoteUiState){
        binding.apply {
            homeQuoteTv.text = quote.quoteEntity?.quote
            homeQuoteAuthorTv.text = quote.quoteEntity?.author
        }
    }
    private fun setFiveDayWeatherUiState(fiveDayWeather : FiveDayWeatherUiState){
        fiveDayWeather.fiveDayWeather?.let {
            fiveDayAdapter.updateRecyclerList(it)
        }
    }

    private fun navigateHomeToFiveDay(entity : LocationEntity){
        binding.homeFiveDayWeatherBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFiveDayWeatherListFragment(
                entity.lat,
                entity.lon
            )
            it.findNavController().navigate(action)
        }
    }
    companion object{
        const val FIRST_SEASON = "first_season"
        const val SECOND_SEASON = "second_season"
    }
}