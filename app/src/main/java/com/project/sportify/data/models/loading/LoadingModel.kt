package com.project.sportify.data.models.loading

import android.widget.ProgressBar
import com.project.sportify.data.models.progress.ProgressTypes

data class LoadingModel(
        val shouldShow: Boolean = false,
        val progressType: ProgressTypes = ProgressTypes.MAIN_PROGRESS,
        var loadingProgressView: ProgressBar? = null,
        var pagingProgressView: ProgressBar? = null,
)
