package com.daylightapp.presentation.home.adapter

import android.view.ViewGroup
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.presentation.base.BaseRecyclerViewAdapter
import com.daylightapp.presentation.base.BaseViewHolder
import com.daylightapp.presentation.common.loadImage
import com.daylightapp.presentation.databinding.AdapterThreeHoursWeatherItemBinding
import com.daylightapp.presentation.utility.inflateAdapterItem

class FiveDayWeatherAdapter : BaseRecyclerViewAdapter<FiveDayWeatherEntity,FiveDayWeatherAdapter.FiveDayViewHolder>() {

    class FiveDayViewHolder(private val binding : AdapterThreeHoursWeatherItemBinding) : BaseViewHolder<FiveDayWeatherEntity>(binding.root) {
        override fun bind(data: FiveDayWeatherEntity, position: Int) {
            binding.itemWindSpeed.text = data.windSpeed
            data.iconId?.let {
                binding.itemFiveDayWeatherIcon.loadImage(it)
            }
            binding.itemFiveDayWeatherDate.text = data.date
            binding.itemFiveDayWeatherCelcius.text = data.celcius
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDayViewHolder {
        return FiveDayViewHolder(parent.inflateAdapterItem(AdapterThreeHoursWeatherItemBinding::inflate))
    }
}