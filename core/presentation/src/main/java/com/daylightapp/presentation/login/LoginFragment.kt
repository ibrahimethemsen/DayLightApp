package com.daylightapp.presentation.login

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daylightapp.presentation.R
import com.daylightapp.presentation.databinding.FragmentLoginBinding
import com.daylightapp.presentation.utility.viewBindingInflater
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBindingInflater(FragmentLoginBinding::bind)
    private val viewModel by viewModels<LoginViewModel>()
    private var group : String = GROUP_A
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickableText("Kayıt Ol",::toRegister,binding.loginRegisterTv)
        observe()
    }
    private fun toRegister(){
        if (group == GROUP_A){
            //single screen
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterSingleScreenFragment()
            findNavController().navigate(action)
        }else if (group == GROUP_B){
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterEpostaPasswordFragment()
            findNavController().navigate(action)
        }
    }

    private fun observe(){
        viewModel.registerScreenABTest.observe(viewLifecycleOwner){
            group = it
        }
    }

    private fun clickableText(
        message : String,
        clickHighOrder : () -> Unit,
        clickText : TextView
    ){
        val spannable = SpannableString(message)
        val clickable : ClickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                clickHighOrder.invoke()
            }
        }
        spannable.setSpan(
            clickable,
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        clickText.text = spannable
        clickText.movementMethod = LinkMovementMethod.getInstance()
    }
    companion object{
        const val GROUP_A = "group_a"
        const val GROUP_B = "group_b"
    }
}