package com.daylightapp.presentation.weatherlist.adapter


import android.view.ViewGroup
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.presentation.base.BaseRecyclerViewAdapter
import com.daylightapp.presentation.base.BaseViewHolder
import com.daylightapp.presentation.common.loadImage
import com.daylightapp.presentation.databinding.AdapterFiveDayWeatherItemBinding
import com.daylightapp.presentation.utility.inflateAdapterItem

class FiveDayWeatherAdapter : BaseRecyclerViewAdapter<DetailFiveDayWeatherEntity,FiveDayWeatherAdapter.FiveDayWeatherListViewHolder>(){
    class FiveDayWeatherListViewHolder(private val binding : AdapterFiveDayWeatherItemBinding) : BaseViewHolder<DetailFiveDayWeatherEntity>(binding = binding.root) {
        override fun bind(data: DetailFiveDayWeatherEntity, position: Int) {
            data.iconId?.let {
                binding.itemFiveDayIcon.loadImage(it)
            }
            binding.itemFiveDayCelcius.text = data.tempCelcius
            binding.itemFiveDayDate.text = data.date
            binding.itemFiveDayWeatherMain.text = data.weatherParameter
            binding.itemFiveDayHumidity.text = data.humidity
            binding.itemFiveDayWindSpeed.text = data.windSpeed
            binding.itemFiveDayWindDeg.text = data.windDeg
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FiveDayWeatherListViewHolder {
        return FiveDayWeatherListViewHolder(parent.inflateAdapterItem(AdapterFiveDayWeatherItemBinding::inflate))
    }
}