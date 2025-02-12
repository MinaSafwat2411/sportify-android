package com.project.sportify.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.project.sportify.ui.screens.splash.composable.SplashScreen
import androidx.hilt.navigation.compose.hiltViewModel

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
        onNavigationRequested = {}
    )
}