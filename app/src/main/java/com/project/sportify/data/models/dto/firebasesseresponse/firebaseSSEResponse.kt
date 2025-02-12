package com.project.sportify.data.models.dto.firebasesseresponse

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
sealed  class FirebaseSSEResponse<T> (
    @SerializedName("data")
    val data: T?
)