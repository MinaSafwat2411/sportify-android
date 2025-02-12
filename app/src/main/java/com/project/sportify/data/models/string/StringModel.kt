package com.project.sportify.data.models.string

import android.content.Context
import com.project.sportify.utils.Constants
import com.project.sportify.utils.alternate

data class StringModel(private val context: Context, private val message: Any?) {
    fun getString(): String {
        return when (message) {
            is String -> message.alternate()
            is Int -> context.getString(message)
            else -> Constants.General.DASH_TEXT
        }
    }
}