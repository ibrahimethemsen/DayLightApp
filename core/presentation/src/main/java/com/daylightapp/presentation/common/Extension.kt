package com.daylightapp.presentation.common

import android.view.View

infix fun View.isVisibility(visible : Boolean){
    if (visible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

infix fun View.nullVisibility(visible : Any?){
    if (visible != null) this.visibility = View.VISIBLE else this.visibility = View.GONE
}
