package com.daylightapp.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T:Any,VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {
    private val recyclerList = mutableListOf<T>()

    fun updateRecyclerList(newList : List<T>){
        recyclerList.clear()
        recyclerList.addAll(newList)
        notifyDataSetChanged()
    }

    fun getItem(position : Int) = recyclerList[position]
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount(): Int = recyclerList.size

    override fun onBindViewHolder(holder: VH, position: Int) {

    }
}