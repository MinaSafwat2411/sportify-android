package com.project.sportify.ui.screens.splash.contract

import com.project.sportify.ui.base.ViewEvent
import com.project.sportify.ui.base.ViewSideEffect
import com.project.sportify.ui.base.ViewState

class SplashContract {
    sealed class Event : ViewEvent {
        data object NavigateToNextScreen : Event()
    }
    data class State(
        val isError: Boolean = false,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object OnBoarding : Navigation()
            data object ChooseWay : Navigation()
        }
    }
}