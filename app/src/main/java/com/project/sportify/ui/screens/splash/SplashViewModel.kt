package com.project.sportify.ui.screens.splash

import com.project.sportify.ui.base.BaseViewModel
import com.project.sportify.ui.screens.splash.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

):BaseViewModel<SplashContract.Event,SplashContract.State,SplashContract.Effect>(){


    init {
        setEvent(event = SplashContract.Event.NavigateToNextScreen)
    }

    override fun setInitialState() = SplashContract.State(
        isError = false
    )

    override fun handleEvents(event: SplashContract.Event) {
        when(event){
            SplashContract.Event.NavigateToNextScreen -> {
            }
        }
    }
}