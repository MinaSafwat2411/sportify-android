package com.project.sportify.ui.screens.onboarding

import com.project.sportify.domain.usecases.onboarding.IOnBoardingUseCase
import com.project.sportify.ui.base.BaseViewModel
import com.project.sportify.ui.screens.onboarding.contract.OnBoardingContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCase: IOnBoardingUseCase
): BaseViewModel<OnBoardingContract.Event, OnBoardingContract.State, OnBoardingContract.Effect>(){
    init {
        setState {
            copy(
                isDarkMode=useCase.isDarkMode()
            )
        }
    }
    override fun setInitialState()= OnBoardingContract.State(
        isError = false,
        isDarkMode = false,
    )

    override suspend fun handleEvents(event: OnBoardingContract.Event) {
        when(event){
            is OnBoardingContract.Event.NavigateToNextScreen -> {
               navigateToNextScreen(event)
            }
            OnBoardingContract.Event.OnSkipClicked ->{
                onSkipClicked()
            }
        }
    }
    private fun  navigateToNextScreen(event: OnBoardingContract.Event.NavigateToNextScreen){
        setState {
            copy(
                currentPage = event.page
            )
        }
        if (viewState.value.currentPage>4){
            setEffect {
                useCase.open()
                OnBoardingContract.Effect.Navigation.ChooseWay
            }
        }
    }
    private  fun  onSkipClicked(){
        setEffect {
            useCase.open()
            OnBoardingContract.Effect.Navigation.ChooseWay
        }
    }
}