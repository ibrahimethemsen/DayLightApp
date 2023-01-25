package com.ibrahimethemsen.daylightapp.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ibrahimethemsen.daylightapp.common.isVisibility
import com.ibrahimethemsen.daylightapp.common.nullVisibility
import com.ibrahimethemsen.daylightapp.databinding.FragmentOnBoardingBinding
import com.ibrahimethemsen.daylightapp.presentation.onboarding.adapter.CityRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {
    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!


    private val viewModel by viewModels<OnBoardingViewModel>()
    private val cityAdapter = CityRecyclerViewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

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
            viewModel.readFromDataStore.collect {
                println("lan ${it.lan}")
                println("lon ${it.lon}")
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