package com.daylightapp.presentation.register.multiscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentRegisterEpostaPasswordBinding
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterEpostaPasswordFragment : Fragment(R.layout.fragment_register_eposta_password) {
    private val binding by viewBindingInflater(FragmentRegisterEpostaPasswordBinding::bind)

    @Inject
    lateinit var analytics: FirebaseAnalytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }

    private fun listener(){
        binding.apply {
            registerMultiToUserBtn.setOnClickListener {
                toRegisterNameSurname()
            }
        }
    }
    private val parameters = Bundle().apply {
        this.putString(FirebaseAnalytics.Event.SELECT_CONTENT,"multiEpostaBtn")
    }

    private fun toRegisterNameSurname(){
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,parameters)
        val action = RegisterEpostaPasswordFragmentDirections.actionRegisterEpostaPasswordFragmentToRegisterNameSurnameFragment()
        findNavController().navigate(action)
    }
}