package com.daylightapp.presentation.onboarding.adapter

import android.view.ViewGroup
import com.daylightapp.domain.entity.city.LocationEntity
import com.daylightapp.presentation.base.BaseRecyclerViewAdapter
import com.daylightapp.presentation.base.BaseViewHolder
import com.daylightapp.presentation.databinding.AdapterCityItemBinding
import com.daylightapp.presentation.utility.inflateAdapterItem

typealias clickTypeAlias = (LocationEntity) -> Unit
class CityRecyclerViewAdapter : BaseRecyclerViewAdapter<LocationEntity,CityViewHolder>() {

    //click item
    private lateinit var onCityClickListener: clickTypeAlias

    fun onCityClickListener(cityClick: clickTypeAlias) {
        this.onCityClickListener = cityClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            parent.inflateAdapterItem(AdapterCityItemBinding::inflate),
            onCityClickListener
        )
    }
}
class CityViewHolder(
    private val binding : AdapterCityItemBinding,
    private val onCityClickListener: clickTypeAlias
) : BaseViewHolder<LocationEntity>(binding.root) {
    override fun bind(data: LocationEntity, position: Int) {
        binding.cityItemNameTv.text = data.name
        binding.cityItemPlateTv.text = data.plate
        binding.root.setOnClickListener {
            onCityClickListener.invoke(data)
        }
    }


}