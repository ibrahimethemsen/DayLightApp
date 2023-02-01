package com.daylightapp.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.Constants.HOME_FRAGMENT
import com.daylightapp.presentation.common.isVisibility
import com.daylightapp.presentation.common.nullVisibility
import com.daylightapp.presentation.databinding.FragmentOnBoardingBinding
import com.daylightapp.presentation.onboarding.adapter.CityRecyclerViewAdapter
import com.daylightapp.presentation.utility.viewBindingInflater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val binding by viewBindingInflater(FragmentOnBoardingBinding::bind)
    private val viewModel by viewModels<OnBoardingViewModel>()
    private val cityAdapter = CityRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStartDestination()
        viewModel.getList()
        binding.onboardingSearchCityRv.adapter = cityAdapter
        binding.onboardingSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterCityList(newText).also {
                    cityAdapter.updateListCity(it)
                }
                return true
            }
        })
        observer()
        cityAdapter.onCityClickListener(::cityWriteDataStore)
    }

    private fun cityWriteDataStore(city: LocationEntity) {
        viewModel.writeDataStoreCity(city.lat, city.lon, city.name,city.plate).also {
            val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment()
            requireView().findNavController().navigate(action)
        }
    }

    private fun observer() {
        viewModel.cityList.observe(viewLifecycleOwner) { onBoardingUi ->
            onBoardingUi.data?.let {
                cityAdapter.updateListCity(it)
            }
            binding.onboardingErrorTv nullVisibility onBoardingUi.error
            binding.onboardingProgress isVisibility onBoardingUi.loading
        }
        viewModel.navStartDestination.observe(viewLifecycleOwner) { destination ->
            if (destination == HOME_FRAGMENT) {
                val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment()
                requireView().findNavController().navigate(action)
            }
        }

    }
}