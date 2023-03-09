package com.daylightapp.presentation.home.model

import com.google.gson.annotations.SerializedName

data class LanguageModel (
    @SerializedName("language_visibility")
    val languageVisibility : Boolean,
    @SerializedName("language_title")
    val languageTitle : String,
    @SerializedName("language_description")
    val languageDescription : String
)
