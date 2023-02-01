package com.daylightapp.presentation.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.loadImage
import com.daylightapp.presentation.common.observeIfNotNull
import com.daylightapp.presentation.databinding.FragmentHomeBinding
import com.daylightapp.presentation.home.adapter.FiveDayWeatherAdapter
import com.daylightapp.presentation.utility.viewBindingInflater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBindingInflater(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val fiveDayAdapter = FiveDayWeatherAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observe()
    }

    private fun initAdapter(){
        binding.homeFiveDayWeatherRv.adapter = fiveDayAdapter
    }

    private fun observe() {
        observeIfNotNull(viewModel.homeUiState,::setHomeUiState)
        observeIfNotNull(viewModel.quoteUiState,::setQuoteUiState)
        observeIfNotNull(viewModel.fiveDayWeather,::setFiveDayWeatherUiState)
        observeIfNotNull(viewModel.locationLatLon,::navigateHomeToFiveDay)
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
}