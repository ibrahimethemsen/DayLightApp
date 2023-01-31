package com.daylightapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daylightapp.domain.entity.weather.FiveDayWeatherEntity
import com.daylightapp.presentation.common.loadImage
import com.daylightapp.presentation.databinding.AdapterFiveDayWeatherItemBinding

class FiveDayWeatherAdapter : RecyclerView.Adapter<FiveDayWeatherAdapter.FiveDayViewHolder>() {

    private val fiveDayWeatherList  = mutableListOf<FiveDayWeatherEntity>()

    fun updateFiveDayWeatherList(newWeatherList : List<FiveDayWeatherEntity>){
        fiveDayWeatherList.clear()
        fiveDayWeatherList.addAll(newWeatherList)
        notifyDataSetChanged()
    }

    class FiveDayViewHolder(private val binding : AdapterFiveDayWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(positionWeather : FiveDayWeatherEntity){
            binding.itemWindSpeed.text = positionWeather.windSpeed
            positionWeather.iconId?.let {
                binding.itemFiveDayWeatherIcon.loadImage(it)
            }
            binding.itemFiveDayWeatherDate.text = positionWeather.date
            binding.itemFiveDayWeatherCelcius.text = positionWeather.celcius
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDayViewHolder {
        val binding = AdapterFiveDayWeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FiveDayViewHolder(binding)
    }

    override fun getItemCount(): Int =fiveDayWeatherList.size

    override fun onBindViewHolder(holder: FiveDayViewHolder, position: Int) {
        holder.bind(fiveDayWeatherList[position])
    }
}