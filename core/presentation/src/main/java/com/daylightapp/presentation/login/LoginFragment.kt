package com.daylightapp.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentLoginBinding
import com.daylightapp.presentation.utility.viewBindingInflater
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBindingInflater(FragmentLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}