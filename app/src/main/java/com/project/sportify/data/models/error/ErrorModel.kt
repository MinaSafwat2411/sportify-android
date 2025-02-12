package com.project.sportify.data.models.error

import androidx.annotation.DrawableRes
import com.project.sportify.data.models.string.StringModel

data class ErrorModel(
    val error: StringModel,
    @DrawableRes val errorIcon: Int
)
