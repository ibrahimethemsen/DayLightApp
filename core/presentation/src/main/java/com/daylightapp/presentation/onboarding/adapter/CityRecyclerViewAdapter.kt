package com.daylightapp.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.presentation.base.BaseRecyclerViewAdapter
import com.daylightapp.presentation.base.BaseViewHolder

import com.daylightapp.presentation.databinding.AdapterCityItemBinding


class CityRecyclerViewAdapter : BaseRecyclerViewAdapter<LocationEntity,CityViewHolder>() {
    private val citys = mutableListOf<LocationEntity>()

    fun updateListCity(newCitys: List<LocationEntity>) {
        citys.apply {
            clear()
            addAll(newCitys)
        }
        notifyDataSetChanged()
    }

    //click item
    private lateinit var onCityClickListener: (LocationEntity) -> Unit

    fun onCityClickListener(cityClick: (LocationEntity) -> Unit) {
        this.onCityClickListener = cityClick
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            AdapterCityItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = citys.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(citys[position])

    }
}

class CityViewHolder(
    private val binding : AdapterCityItemBinding
) : BaseViewHolder<LocationEntity>(binding.root) {
    init {
        binding.root.setOnClickListener {

        }
    }
    override fun bind(data: LocationEntity) {
        binding.cityItemNameTv.text = data.name
        binding.cityItemPlateTv.text = data.plate
    }

}