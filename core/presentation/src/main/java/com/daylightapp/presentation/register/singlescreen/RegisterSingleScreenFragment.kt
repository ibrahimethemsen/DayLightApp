package com.daylightapp.presentation.register.singlescreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.daylightapp.common.NetworkResult
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentRegisterSingleScreenBinding
import com.daylightapp.presentation.register.RegisterViewModel
import com.daylightapp.presentation.utility.AnalyticsUtil
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class RegisterSingleScreenFragment : Fragment(R.layout.fragment_register_single_screen) {
    private val binding by viewBindingInflater(FragmentRegisterSingleScreenBinding::bind)

    @Inject
    lateinit var analytics: FirebaseAnalytics
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }

    private fun listener(){
        binding.apply {
            registerSingleBtn.setOnClickListener {
                checkAccount(binding.registerSingleEpostaEt.text.toString(),binding.registerSinglePasswordEt.text.toString())
            }
        }
    }

    private fun checkAccount(
        eposta : String?,
        password : String?
    ){
        val empty = viewModel.isEmpty(eposta,password)
        if (empty){
            viewModel.createAccountEmail(eposta!!,password!!).onEach {
                when(it){
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(),it.exception.message,Toast.LENGTH_LONG).show()
                    }
                    is NetworkResult.Success -> {
                        toHomeFragment()
                    }
                    else -> {}
                }
            }.launchIn(lifecycleScope)
        }else{
            Toast.makeText(requireContext(),"Boş alan Bırakmayınız",Toast.LENGTH_LONG).show()
        }
    }

    private fun toHomeFragment(){
        AnalyticsUtil.apply {
            putBundleString(FirebaseAnalytics.Event.SELECT_ITEM,"toHomeFragment Btn")
            eventLog(analytics,"toHomeFragment")
        }

        val action = RegisterSingleScreenFragmentDirections.actionRegisterSingleScreenFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}