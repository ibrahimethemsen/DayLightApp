package com.daylightapp.presentation.feedback

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.daylightapp.common.NetworkResult
import com.daylightapp.presentation.R
import com.daylightapp.presentation.common.Constants.FEEDBACK_KEY
import com.daylightapp.presentation.databinding.FragmentFeedBackBottomBinding
import com.daylightapp.presentation.utility.SharedManager
import com.daylightapp.presentation.utility.viewBindingInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FeedBackBottomFragment : BottomSheetDialogFragment(R.layout.fragment_feed_back_bottom) {
    private val binding by viewBindingInflater(FragmentFeedBackBottomBinding::bind)
    private val viewModel by viewModels<FeedBackViewModel>()
    private val args by navArgs<FeedBackBottomFragmentArgs>()
    private var collectionId : String = "empty"
    private var sharedKey : String = "empty_shared"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.BottomSheetDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectionId = args.collectionId
        sharedKey = args.sharedKey
        binding.featureFeedBackTitleTv.text = args.title
        listener()
    }

    private fun listener() {
        binding.apply {
            featureFeedBackSendBtn.setOnClickListener {
                sendToFeedBack(binding.featureFeedBackDescriptionEt.text.toString())
            }
        }
    }

    private fun sendToFeedBack(message : String?) {
        if (!message.isNullOrBlank()){
            val shared = SharedManager(requireContext())
            viewModel.sendFeedBack(collectionId,message).onEach{
                when(it){
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(),"Teknik bir arıza oldu",Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        Toast.makeText(requireContext(),"Geribildiriminiz gönderildi",Toast.LENGTH_SHORT).show()
                        shared.setSharedPreference(FEEDBACK_KEY,sharedKey)
                        findNavController().popBackStack()
                    }
                }
            }.launchIn(lifecycleScope)
        }else{
            Toast.makeText(requireContext(),"Mesaj giriniz",Toast.LENGTH_LONG).show()
        }
    }


}