package com.daylightapp.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.Constants.HOME_FRAGMENT
import com.daylightapp.presentation.common.isVisibility
import com.daylightapp.presentation.common.observeIfNotNull
import com.daylightapp.presentation.common.setVisibility
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

        initAdapter()
        setCityFilterList()
        observer()
        initListerners()
    }
    private fun initAdapter(){
        binding.onboardingSearchCityRv.adapter = cityAdapter
    }

    private fun initListerners(){
        cityAdapter.onCityClickListener(::writeCityDataStore)
    }

    private fun setCityFilterList(){
        binding.onboardingSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterCityList(newText).let {
                    cityAdapter.updateRecyclerList(it)
                }
                return true
            }
        })
    }

    private fun writeCityDataStore(city: LocationEntity) {
        viewModel.writeDataStoreCity(city.lat, city.lon, city.name,city.plate)
        navigateHome()
    }

    private fun observer() {
        observeIfNotNull(viewModel.cityList,::setOnBoardingUiState)
        observeIfNotNull(viewModel.navStartDestination,::navStartDestination)
    }

    private fun navStartDestination(destination : String){
        if (destination == HOME_FRAGMENT) {
            navigateHome()
        }
    }

    private fun setOnBoardingUiState(onBoardingUi : OnBoardingUi){
        onBoardingUi.data?.let {
            cityAdapter.updateRecyclerList(it)
        }
        binding.onboardingErrorTv setVisibility onBoardingUi.error
        binding.onboardingProgress isVisibility onBoardingUi.loading
    }

    private fun navigateHome(){
        val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}