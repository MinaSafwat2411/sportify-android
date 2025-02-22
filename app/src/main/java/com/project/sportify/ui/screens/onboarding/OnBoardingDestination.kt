package com.project.sportify.ui.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project.sportify.ui.main.NavigationScreen
import com.project.sportify.ui.screens.onboarding.composable.OnBoardingScreen
import com.project.sportify.ui.screens.onboarding.contract.OnBoardingContract

@Composable
fun OnBoardingDestination(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    navController: NavHostController
) {
    OnBoardingScreen(
        state = viewModel.viewState.value,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is OnBoardingContract.Effect.Navigation.ChooseWay -> {
                    navController.navigate(NavigationScreen.ChooseWay.route)
                }
            }
        },
        effectFlow = viewModel.effect
    )
}