package com.project.sportify.ui.screens.splash.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.project.sportify.R
import com.project.sportify.ui.screens.splash.contract.SplashContract
import com.project.sportify.ui.theme.White
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    state: SplashContract.State,
    effectFlow: Flow<SplashContract.Effect>?,
    onEventSent: (event: SplashContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: SplashContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(effectFlow) {
        effectFlow?.onEach{ effect ->
            when (effect) {
                is SplashContract.Effect.Navigation.OnBoarding -> onNavigationRequested(effect)
                is SplashContract.Effect.Navigation.ChooseWay -> onNavigationRequested(effect)
            }
        }?.collect()
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {

            else -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo),
                    contentDescription =""
                )
            }
        }
    }
}