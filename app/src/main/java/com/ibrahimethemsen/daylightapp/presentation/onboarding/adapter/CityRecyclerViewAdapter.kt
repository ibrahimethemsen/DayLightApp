package com.ibrahimethemsen.daylightapp.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimethemsen.daylightapp.data.dto.city.City
import com.ibrahimethemsen.daylightapp.databinding.AdapterCityItemBinding

class CityRecyclerViewAdapter : RecyclerView.Adapter<CityRecyclerViewAdapter.CityViewHolder>() {
    private val citys = mutableListOf<City>()

    fun updateListCity(newCitys : List<City>){
        citys.apply {
            clear()
            addAll(newCitys)
        }
        notifyDataSetChanged()
    }
    //click item
    private lateinit var onCityClickListener : (City) -> Unit

    fun onCityClickListener(cityClick : (City)->Unit){
        this.onCityClickListener = cityClick
    }

    class CityViewHolder(val binding : AdapterCityItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(city : City){
            binding.cityItemNameTv.text = city.name
            binding.cityItemPlateTv.text = city.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(AdapterCityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = citys.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(citys[position])
        holder.binding.root.setOnClickListener {
            onCityClickListener.invoke(citys[position])
        }
    }
}