package com.daylightapp.presentation.register.singlescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentRegisterSingleScreenBinding
import com.daylightapp.presentation.utility.AnalyticsUtil
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterSingleScreenFragment : Fragment(R.layout.fragment_register_single_screen) {
    private val binding by viewBindingInflater(FragmentRegisterSingleScreenBinding::bind)

    @Inject
    lateinit var analytics: FirebaseAnalytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }

    private fun listener(){
        binding.apply {
            registerSingleBtn.setOnClickListener {
                toHomeFragment()
            }
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