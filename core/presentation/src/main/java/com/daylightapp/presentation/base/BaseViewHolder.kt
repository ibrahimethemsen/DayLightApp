package com.daylightapp.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any>(binding : View) : RecyclerView.ViewHolder(binding) {
    abstract fun bind(data : T,position : Int)
}