package com.ibrahimethemsen.daylightapp.presentation.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibrahimethemsen.daylightapp.R
import com.ibrahimethemsen.daylightapp.databinding.FragmentHomeBinding
import com.ibrahimethemsen.daylightapp.utility.viewBindingInflater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBindingInflater(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFiveWeather()
        observe()
    }
    private fun observe(){
        viewModel.homeUiState.observe(viewLifecycleOwner){
            binding.title.text = it
        }
    }
}