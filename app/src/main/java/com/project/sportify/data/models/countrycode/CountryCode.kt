package com.project.sportify.data.models.countrycode

import com.google.gson.annotations.SerializedName

data class CountryCode(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("countryName")
    val countryName: String? = null,
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("id")
    val id: Int? = null
)