package com.daylightapp.presentation.home.model

import com.google.gson.annotations.SerializedName

data class NewFeature(
    @SerializedName("feature_visibility")
    val featureVisibility : Boolean,
    @SerializedName("feature_title")
    val featureTitle : String,
    @SerializedName("feature_message")
    val featureMessage : String,
    @SerializedName("feature_positive")
    val positiveUrl : String
)
