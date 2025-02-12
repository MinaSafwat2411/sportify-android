package com.project.sportify.ui.main

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.sportify.R
import com.project.sportify.ui.screens.splash.SplashScreenDestination


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel,
    startDestination: String = NavigationScreen.Splash.route,
    ) {
    val context = LocalContext.current
    var shouldShowBottomNavigationBar by rememberSaveable {
        mutableStateOf(false)
    }

    var isHomeResponseFinished by rememberSaveable {
        mutableStateOf(true)
    }


    var selectedRoute by rememberSaveable {
        mutableStateOf(NavigationScreen.BottomNavItem.Home.route)
    }
    LaunchedEffect(key1 = viewModel.viewState.value.shouldShowBottomNavBar) {
        shouldShowBottomNavigationBar = viewModel.viewState.value.shouldShowBottomNavBar
    }

    LaunchedEffect(key1 = viewModel.viewState.value.selectedItem.route) {
        selectedRoute = viewModel.viewState.value.selectedItem.route
    }

    var currentDestinationBeforeUnAuth by rememberSaveable {
        mutableStateOf("Home")
    }
    Scaffold(
        modifier = modifier,
    ){
        NavHost(
            modifier=modifier,
            navController = navController,
            startDestination = startDestination,
        ){
            composable(NavigationScreen.Splash.route) {
                ShouldShowNavigationBottomBar(NavigationScreen.Splash.route)
                SplashScreenDestination(navController = navController)
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
private fun ShouldShowNavigationBottomBar(route: String) {
    val activity = LocalContext.current as MainActivity
    when (route) {
        NavigationScreen.Splash.route,
            -> activity.hideBottomNavigation()

        else -> activity.showBottomNavigation()
    }
}

enum class Screen {
    SPLASH,HOME
}

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen(Screen.SPLASH.name)
    data object Home : NavigationScreen(Screen.HOME.name)

    sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int) {
        data object Home : BottomNavItem(NavigationScreen.Home.route, R.drawable.ic_home)

    }
}
