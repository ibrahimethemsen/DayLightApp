package com.daylightapp.presentation.register.multiscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentRegisterNameSurnameBinding
import com.daylightapp.presentation.utility.AnalyticsUtil
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterNameSurnameFragment : Fragment(R.layout.fragment_register_name_surname) {
    private val binding by viewBindingInflater(FragmentRegisterNameSurnameBinding::bind)

    @Inject
    lateinit var analytics: FirebaseAnalytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }


    private fun listener(){
        binding.apply {
            registerMultiCompleteBtn.setOnClickListener {
                toHomeFragment()
            }
        }
    }

    private fun toHomeFragment(){
        AnalyticsUtil.apply {
            putBundleString(FirebaseAnalytics.Event.SELECT_CONTENT,"Completed Register")
            eventLog(analytics,FirebaseAnalytics.Event.SELECT_ITEM)
        }
        val action = RegisterNameSurnameFragmentDirections.actionRegisterNameSurnameFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}