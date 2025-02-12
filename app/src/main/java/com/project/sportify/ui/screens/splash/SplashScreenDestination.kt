package com.project.sportify.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.project.sportify.ui.screens.splash.composable.SplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.sportify.ui.main.NavigationScreen
import com.project.sportify.ui.screens.splash.contract.SplashContract

@Composable
fun SplashScreenDestination(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController
){
    SplashScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = {navigationEffect ->
            when(navigationEffect){
                is SplashContract.Effect.Navigation.OnBoarding -> {
                    navController.navigate(NavigationScreen.OnBoarding.route)
                }
                is SplashContract.Effect.Navigation.ChooseWay->{
                    navController.navigate(NavigationScreen.ChooseWay.route)
                }
            }
        }
    )
}