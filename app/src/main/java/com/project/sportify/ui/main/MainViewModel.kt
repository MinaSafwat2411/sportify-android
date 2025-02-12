package com.project.sportify.ui.main

import com.project.sportify.ui.base.BaseViewModel
import com.project.sportify.ui.main.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {
    override fun setInitialState() = MainContract.State(
        shouldShowBottomNavBar = false,
        selectedItem = NavigationScreen.BottomNavItem.Home
    )

    override suspend fun handleEvents(event: MainContract.Event) {
        when (event) {
            MainContract.Event.HideBottomNavigationBar -> {
                setState {
                    copy(
                        shouldShowBottomNavBar = false
                    )
                }
            }

            MainContract.Event.ShowBottomNavigationBar -> {
                setState {
                    copy(
                        shouldShowBottomNavBar = true
                    )
                }
            }

            is MainContract.Event.NavigateItem -> {
                setState {
                    copy(selectedItem = event.item)
                }
            }
        }
    }
}