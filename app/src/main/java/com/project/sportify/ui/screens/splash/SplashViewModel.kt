package com.project.sportify.ui.screens.splash

import com.project.sportify.domain.usecases.splash.ISplashUseCase
import com.project.sportify.ui.base.BaseViewModel
import com.project.sportify.ui.screens.splash.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: ISplashUseCase
) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    init {
        setState {
            copy(
                isDarkMode=useCase.isDarkMode()
            )
        }
        setEvent(event = SplashContract.Event.NavigateToNextScreen)
    }

    override fun setInitialState() = SplashContract.State(
        isError = false,
    )

    override suspend fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.NavigateToNextScreen -> {
                navigateToNextScreen()
            }
        }
    }

    private suspend fun navigateToNextScreen() {
        delay(4000)
        if (useCase.shouldNavigateToWhichScreen()) {
            setEffect { SplashContract.Effect.Navigation.ChooseWay }
        } else {
            setEffect { SplashContract.Effect.Navigation.OnBoarding }
        }
    }
}
