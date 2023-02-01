package com.daylightapp.presentation.weatherlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.observeIfNotNull
import com.daylightapp.presentation.databinding.FragmentFiveDayWeatherListBinding
import com.daylightapp.presentation.utility.viewBindingInflater
import com.daylightapp.presentation.weatherlist.adapter.FiveDayWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiveDayWeatherListFragment : Fragment(R.layout.fragment_five_day_weather_list) {
    private val viewModel by viewModels<FiveDayWeatherListViewModel>()
    private val binding by viewBindingInflater(FragmentFiveDayWeatherListBinding::bind)
    private val fiveDayAdapter = FiveDayWeatherAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initAdapter()
        observeIfNotNull(viewModel.detailFiveUiState, ::setDetailUiState)
    }

    private fun initAdapter() {
        binding.fiveDayWeatherListRv.adapter = fiveDayAdapter
    }

    private fun initListeners() {
        with(binding) {
            fiveDayBackBtn.setOnClickListener { navigateHome() }
        }
    }

    private fun setDetailUiState(detailFiveUiState: DetailFiveUiState) {
        fiveDayAdapter.updateRecyclerList(detailFiveUiState.data)
    }

    private fun navigateHome() {
        val action =
            FiveDayWeatherListFragmentDirections.actionFiveDayWeatherListFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}

