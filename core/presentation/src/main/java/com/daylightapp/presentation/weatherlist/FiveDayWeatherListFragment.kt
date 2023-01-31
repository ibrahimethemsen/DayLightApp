package com.daylightapp.presentation.weatherlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentFiveDayWeatherListBinding
import com.daylightapp.presentation.utility.viewBindingInflater
import com.daylightapp.presentation.weatherlist.adapter.FiveDayWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiveDayWeatherListFragment : Fragment(R.layout.fragment_five_day_weather_list) {
    private val viewModel by viewModels<FiveDayWeatherListViewModel>()
    private val binding by viewBindingInflater(FragmentFiveDayWeatherListBinding::bind)
    private val args : FiveDayWeatherListFragmentArgs by navArgs()
    private val fiveDayAdapter = FiveDayWeatherAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lat = args.lat
        val lon = args.lon
        viewModel.getFiveDayWeatherUseCase(lat,lon)
        binding.fiveDayBackBtn.setOnClickListener {
            val action = FiveDayWeatherListFragmentDirections.actionFiveDayWeatherListFragmentToHomeFragment()
            it.findNavController().navigate(action)
        }
        observe()
        binding.fiveDayWeatherListRv.adapter = fiveDayAdapter
    }

    private fun observe(){
        viewModel.detailFiveUiState.observe(viewLifecycleOwner){detailListUiState ->
            fiveDayAdapter.updateFiveDay(detailListUiState.data)
            println()
        }
    }
}