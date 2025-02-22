package com.project.sportify.domain.base


import androidx.annotation.CallSuper
import com.project.sportify.data.models.status.Status
import com.project.sportify.data.models.status.StatusCode
import com.project.sportify.ui.base.IBaseUseCase



open class BaseUseCase(private val mBaseRepository: IBaseRepository) : IBaseUseCase {

    @CallSuper
    protected fun<T> validateResponse(statusResponse: Status<T>?): StatusCode {
        if (statusResponse == null)
            return StatusCode.ERROR

        if (statusResponse.isOfflineData())
            return StatusCode.OFFLINE_DATA

        if (statusResponse.isIdle())
            return StatusCode.IDLE

        if (statusResponse.isNoNetwork())
            return StatusCode.NO_NETWORK

        if (statusResponse.isError())
            return StatusCode.ERROR

        if(statusResponse.isNotAuthorized())
            return StatusCode.NOT_AUTHORIZED

        if(statusResponse.isForbidden())
            return StatusCode.FORBIDDEN

        if (statusResponse.data == null)
            return StatusCode.ERROR

        if (statusResponse.isServerError())
            return StatusCode.SERVER_ERROR

        return StatusCode.VALID
    }

    fun <T> onServerError(status: Status<T>): Status<T> {
        return if (status.error.isNullOrBlank())
            Status.Error(error = "")
        else
            Status.ServerError(
                error = status.error
            )
    }

    override fun isDarkMode(): Boolean {
        return mBaseRepository.isDarkMode()
    }
}
