package com.project.sportify.ui.base

import androidx.lifecycle.viewModelScope
import com.project.sportify.data.models.progress.ProgressTypes
import com.project.sportify.data.models.status.Status
import com.project.sportify.utils.alternate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BasePagingViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> :
    BaseViewModel<Event, UiState, Effect>() {

    protected var shouldLoadMore: Boolean = true
    protected var isLoading: Boolean = false
    private var noNetworkToastCount = 0
    var shouldClearObservable = MutableSharedFlow<Boolean>()
    public override fun onCleared() {
        isLoading = false
        super.onCleared()
    }

    private fun incrementNoNetworkCount() {
        noNetworkToastCount++
    }

    fun resetNoNetworkCount() {
        noNetworkToastCount = 0
    }

    private fun showNoNetworkErrorForPaging(onNetworkError: () -> Unit) {
        incrementNoNetworkCount()
        onNetworkError()
    }

    private fun <T> handleNoNetworkErrorForPaging(
        status: Status<T>,
        onError: (error: String?) -> Unit
    ) {
        if (status.isNoNetwork()) {
            if (noNetworkToastCount == 0) {
                showNoNetworkErrorForPaging {
                    onError(status.error.alternate())
                }
            }
        } else {
            onError(status.error.alternate())
        }
    }

    fun <T> handleStatusErrorWithExistingData(
        progressType: ProgressTypes,
        status: Status<T>,
        onError: (error: String?) -> Unit
    ) {
        viewModelScope.launch {
            if (progressType == ProgressTypes.PAGING_PROGRESS) {
                handleNoNetworkErrorForPaging(status) {
                    onError(it)
                }
            } else {
                onError(status.error.alternate())
            }
        }

    }
}
