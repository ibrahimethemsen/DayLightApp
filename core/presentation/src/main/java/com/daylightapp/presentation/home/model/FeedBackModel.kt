package com.daylightapp.presentation.home.model

import com.google.gson.annotations.SerializedName

data class FeedBackModel(
    @SerializedName("feedback_visibility")
    val feedbackVisibility : Boolean,
    @SerializedName("feedback_shared")
    val feedbackShared : String,
    @SerializedName("feedback_title")
    val feedbackTitle : String,
    @SerializedName("feedback_collection_id")
    val feedbackCollectionId : String
)
