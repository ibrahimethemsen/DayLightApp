package com.daylightapp.presentation.weatherlist.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daylightapp.domain.entity.weather.DetailFiveDayWeatherEntity
import com.daylightapp.presentation.common.loadImage
import com.daylightapp.presentation.databinding.AdapterFiveDayWeatherItemBinding

class FiveDayWeatherAdapter : RecyclerView.Adapter<FiveDayWeatherAdapter.FiveDayWeatherListViewHolder>() {
    private val fiveDayWeatherList = mutableListOf<DetailFiveDayWeatherEntity>()

    fun updateFiveDay(newList : List<DetailFiveDayWeatherEntity>){
        fiveDayWeatherList.clear()
        fiveDayWeatherList.addAll(newList)
        notifyDataSetChanged()
    }
    class FiveDayWeatherListViewHolder(val binding : AdapterFiveDayWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailEntity : DetailFiveDayWeatherEntity){

            detailEntity.iconId?.let {
                binding.itemFiveDayIcon.loadImage(it)
            }
            binding.itemFiveDayCelcius.text = detailEntity.tempCelcius
            binding.itemFiveDayDate.text = detailEntity.date
            binding.itemFiveDayWeatherMain.text = detailEntity.weatherParameter
            binding.itemFiveDayHumidity.text = detailEntity.humidity
            binding.itemFiveDayWindSpeed.text = detailEntity.windSpeed
            binding.itemFiveDayWindDeg.text = detailEntity.windDeg
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FiveDayWeatherListViewHolder {
        val binding = AdapterFiveDayWeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FiveDayWeatherListViewHolder(binding)
    }

    override fun getItemCount(): Int = fiveDayWeatherList.size

    override fun onBindViewHolder(holder: FiveDayWeatherListViewHolder, position: Int) {
        holder.bind(fiveDayWeatherList[position])
    }
}