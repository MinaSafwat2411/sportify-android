package com.project.sportify.ui.main.contract

import com.project.sportify.ui.base.ViewEvent
import com.project.sportify.ui.base.ViewSideEffect
import com.project.sportify.ui.base.ViewState
import com.project.sportify.ui.main.NavigationScreen

class MainContract {

    sealed class Event : ViewEvent {
        data object ShowBottomNavigationBar : Event()
        data object HideBottomNavigationBar : Event()

        data class NavigateItem(val item: NavigationScreen.BottomNavItem): Event()
    }

    data class State(
        val shouldShowBottomNavBar: Boolean = false,
        val selectedItem: NavigationScreen.BottomNavItem = NavigationScreen.BottomNavItem.Home
    ) : ViewState

    sealed class Effect : ViewSideEffect
}