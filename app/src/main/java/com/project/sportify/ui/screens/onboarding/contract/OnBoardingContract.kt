package com.project.sportify.ui.screens.onboarding.contract

import com.project.sportify.ui.base.ViewEvent
import com.project.sportify.ui.base.ViewSideEffect
import com.project.sportify.ui.base.ViewState

class OnBoardingContract{
    sealed class Event: ViewEvent {
        data class NavigateToNextScreen(val page: Int = 0) : Event()
        data object OnSkipClicked : Event()
    }
    data class State(
        val isError: Boolean = false,
        val isDarkMode: Boolean = false,
        val currentPage: Int = 0
    ):ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ChooseWay : Navigation()
        }
    }
}