package com.ibrahimethemsen.daylightapp.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ibrahimethemsen.daylightapp.R
import com.ibrahimethemsen.daylightapp.common.isVisibility
import com.ibrahimethemsen.daylightapp.common.nullVisibility
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.databinding.FragmentOnBoardingBinding
import com.ibrahimethemsen.daylightapp.presentation.onboarding.adapter.CityRecyclerViewAdapter
import com.ibrahimethemsen.daylightapp.utility.viewBindingInflater
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val binding by viewBindingInflater(FragmentOnBoardingBinding::bind)
    private val viewModel by viewModels<OnBoardingViewModel>()
    private val cityAdapter = CityRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        lifecycleScope.launch {
            viewModel.getLocation.collect{
                println("laton ${it.lat}")
                println("lonon ${it.lon}")
                println("nameon ${it.name}")
            }
        }
        cityAdapter.onCityClickListener(::cityWriteDataStore)
    }

    private fun cityWriteDataStore(city: City) {
        if (!city.latitude.isNullOrEmpty() && !city.longitude.isNullOrEmpty() && !city.name.isNullOrEmpty()) {
            println("on boarding ${city.latitude}")
            println("on boarding ${city.longitude}")
            println("on boarding ${city.name}")
            viewModel.writeDataStoreCity(city.latitude, city.longitude,city.name).also {
                val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment()
                requireView().findNavController().navigate(action)
            }

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
    }
}