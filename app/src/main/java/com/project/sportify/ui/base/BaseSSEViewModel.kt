package com.project.sportify.ui.base

import com.google.gson.Gson
import com.project.sportify.data.models.dto.firebasesseresponse.FirebaseSSEResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import java.util.concurrent.TimeUnit

abstract class BaseSSEViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect>(
    private val useCase: IBaseUseCase,
    private val mGson: Gson,
    private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel<Event, UiState, Effect>() {
    private val client: OkHttpClient =
        OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES).build()

    private var activeEventSource: EventSource? = null

    abstract fun onSSEEvent(event: SSEEvent)

    private fun addSSEListener(sseType: SSEType): EventSourceListener {
        return object : EventSourceListener() {
            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                try {

                  } catch (e: Exception) {
                    onSSEEvent(SSEEvent.Error(type = sseType, error = e.message))
                }
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                onSSEEvent(SSEEvent.Error(type = sseType, error = t?.message))
            }
        }
    }

    protected fun callSSE(sseType: SSEType, sseUrl: String) {
        stopSSE()
        viewModelScope.launch(ioDispatcher) {
            delay(10)

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
        activeEventSource?.cancel()
        client.dispatcher.runningCalls().forEach { it.cancel() }
    }

    private fun stopSSE() {
        activeEventSource?.let {
            it.cancel()
            activeEventSource = null
        }
        client.dispatcher.runningCalls().forEach { it.cancel() }
    }
}

sealed class SSEEvent(
    val type: SSEType,
    val error: String? = null,
    val sseResponse: FirebaseSSEResponse<*>? = null
) {
    class Success<T>(type: SSEType, sseResponse: FirebaseSSEResponse<T>?) :
        SSEEvent(type, sseResponse = sseResponse)

    class Error(type: SSEType, error: String?) : SSEEvent(type, error = error)

    fun isSuccess() = sseResponse != null && error.isNullOrEmpty()
}

enum class SSEType {

}
