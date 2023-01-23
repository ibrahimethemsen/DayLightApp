package com.ibrahimethemsen.daylightapp.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibrahimethemsen.daylightapp.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {
    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!


    private val viewModel by viewModels<OnBoardingViewModel>()
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
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCity(newText)
                return true
            }
        })
        observer()
    }

    private fun filterCity(query:String?){
        query?.let {
            viewModel.cityList.observe(viewLifecycleOwner){cityList->
                cityList.forEach{city ->
                    if (city.name!!.lowercase().contains(it)){
                        println("şehir bulundu ${city.name} $it")
                    }else{
                        println("şehir bulunamadı")
                    }
                }
            }
        }
    }

    private fun observer(){
        viewModel.cityList.observe(viewLifecycleOwner){

        }
    }
}