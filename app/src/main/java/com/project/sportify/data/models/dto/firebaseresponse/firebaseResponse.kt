package com.project.sportify.data.models.dto.firebaseresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FireBaseResponse<T>(
    @SerializedName("status")
    @Expose
    var status: Boolean = false,
    @SerializedName("timeStamp")
    @Expose
    var timeStamp: String? = null,
    @SerializedName("path")
    @Expose
    var path: String? = null,
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null,
    @SerializedName("errors")
    @Expose
    var errors: ArrayList<String>? = null,

    @SerializedName("errorType")
    @Expose
    var errorType: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: T? = null,
) {
    fun isSuccess(): Boolean = status
}