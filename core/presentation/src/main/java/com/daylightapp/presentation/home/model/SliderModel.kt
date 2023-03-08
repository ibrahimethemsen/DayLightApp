package com.daylightapp.presentation.home.model

import com.google.gson.annotations.SerializedName

data class SliderModel(
    @SerializedName("slider_image_url")
    val sliderImage : String,
    @SerializedName("slider_intent_url")
    val intentUrl : String,
    @SerializedName("slider_text")
    val sliderText : String
)