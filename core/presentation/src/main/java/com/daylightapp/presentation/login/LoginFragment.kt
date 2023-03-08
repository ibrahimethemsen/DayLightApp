package com.daylightapp.presentation.login

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.daylightapp.common.NetworkResult
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.loadRemote
import com.daylightapp.presentation.databinding.FragmentLoginBinding
import com.daylightapp.presentation.home.HomeFragment
import com.daylightapp.presentation.utility.AnalyticsUtil
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBindingInflater(FragmentLoginBinding::bind)
    private val viewModel by viewModels<LoginViewModel>()
    private var group : String = GROUP_A

    @Inject
    lateinit var analytics : FirebaseAnalytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickableText("Kayıt Ol",::toRegister,binding.loginRegisterTv)
        observe()
        viewModel.bannerListener()
        viewModel.welcomeBannerListener()
        listener()
    }
    private fun observe(){
        viewModel.registerScreenABTest.observe(viewLifecycleOwner){group = it}
        viewModel.welcomeBanner.observe(viewLifecycleOwner,::welcomeBanner)
        viewModel.bannerListener.observe(viewLifecycleOwner,::bannerInitialize)
    }
    private fun listener(){
        binding.apply {
            loginBtn.setOnClickListener {
                loginAccount(loginEmailEt.text.toString(),loginPasswordEt.text.toString())
                AnalyticsUtil.apply {
                    putBundleString(FirebaseAnalytics.Event.SELECT_CONTENT,"Login")
                    eventLog(analytics,"Login Btn")
                }
            }
        }
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
    private fun bannerInitialize(banner : BannerData){
        if (banner.visibility){
            binding.loginBannerVisibilityGroup.visibility = View.VISIBLE
            when(banner.bannerSeason){
                HomeFragment.FIRST_SEASON -> {
                    binding.loginBannerIv.setImageDrawable(resources.getDrawable(R.drawable.banner_first,requireContext().theme))
                }
                HomeFragment.SECOND_SEASON -> {
                    binding.loginBannerIv.setImageDrawable(resources.getDrawable(R.drawable.banner_two,requireContext().theme))
                }
            }
            binding.loginBannerTv.text = banner.bannerText
        }

    }

    private fun welcomeBanner(banner : WelcomeBanner){
        if (banner.welcomeVisibility){
            binding.apply {
                loginWelcomeBannerGroup.visibility = View.VISIBLE
                loginWelcomeBannerTv.text = banner.welcomeDescription
                loginWelcomeBannerIv.loadRemote(banner.welcomeUrl)
            }
        }
    }
    private fun loginAccount(email : String?,password : String?){
        if (viewModel.isEmpty(email, password)){
            viewModel.loginAccount(email!!,password!!).onEach {
                when(it){
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(),it.exception.message,Toast.LENGTH_LONG).show()
                    }
                    is NetworkResult.Success<Boolean> -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }
                    else -> {}
                }
            }.launchIn(lifecycleScope)
        }else{
            Toast.makeText(requireContext(),"Boş alan bırakmayınız",Toast.LENGTH_LONG).show()
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