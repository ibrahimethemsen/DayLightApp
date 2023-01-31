package com.daylightapp.presentation.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.loadImage
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
        viewModel.getCurrentWeather()
        viewModel.getQuote()
        binding.homeFiveDayWeatherRv.adapter = fiveDayAdapter
        observe()
    }

    private fun observe(){
        viewModel.homeUiState.observe(viewLifecycleOwner){ currentWeather ->
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
        viewModel.quoteUiState.observe(viewLifecycleOwner){quote ->
            binding.homeQuoteTv.text = quote.quoteEntity?.quote
            binding.homeQuoteAuthorTv.text = quote.quoteEntity?.author
        }
        viewModel.fiveDayWeather.observe(viewLifecycleOwner){fiveDayWeather->
            fiveDayWeather.fiveDayWeather?.let {
                fiveDayAdapter.updateFiveDayWeatherList(it)
            }
        }
    }
}