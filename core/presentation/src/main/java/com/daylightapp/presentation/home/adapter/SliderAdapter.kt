package com.daylightapp.presentation.home.adapter


import android.view.ViewGroup
import com.daylightapp.presentation.base.BaseRecyclerViewAdapter
import com.daylightapp.presentation.base.BaseViewHolder
import com.daylightapp.presentation.common.loadRemote
import com.daylightapp.presentation.databinding.AdapterSliderItemBinding
import com.daylightapp.presentation.home.model.SliderModel
import com.daylightapp.presentation.utility.inflateAdapterItem

typealias sliderClickTypeAlias = (String) -> Unit

class SliderAdapter : BaseRecyclerViewAdapter<SliderModel, SliderAdapter.SliderViewHolder>() {

    //click item
    private lateinit var sliderClickListener: sliderClickTypeAlias

    fun sliderClickListener(sliderClick: sliderClickTypeAlias) {
        this.sliderClickListener = sliderClick
    }

    class SliderViewHolder(
        private val binding: AdapterSliderItemBinding,
        private val sliderClick: sliderClickTypeAlias
    ) : BaseViewHolder<SliderModel>(binding.root) {
        override fun bind(data: SliderModel, position: Int) {
            binding.adapterSliderIv.loadRemote(data.sliderImage)
            binding.root.setOnClickListener {
                sliderClick.invoke(data.intentUrl)
            }
            binding.adapterSliderTv.text = data.sliderText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            parent.inflateAdapterItem(AdapterSliderItemBinding::inflate),
            sliderClickListener
        )
    }
}