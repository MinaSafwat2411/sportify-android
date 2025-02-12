package com.project.sportify.ui.main

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
            composable(NavigationScreen.OnBoarding.route) {
                ShouldShowNavigationBottomBar(NavigationScreen.OnBoarding.route)
                Text("OnBoarding")
            }
            composable(NavigationScreen.ChooseWay.route){
                ShouldShowNavigationBottomBar(NavigationScreen.ChooseWay.route)
                Text("ChooseWay")
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
        NavigationScreen.OnBoarding.route,
        NavigationScreen.ChooseWay.route
            -> activity.hideBottomNavigation()

        else -> activity.showBottomNavigation()
    }
}

enum class Screen {
    SPLASH,HOME,ON_BOARDING,LOGIN,REGISTER,FORGOT_PASSWORD,PROFILE,EDIT_PROFILE,CHANGE_PASSWORD,
    CHOOSE_WAY
}

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen(Screen.SPLASH.name)
    data object Home : NavigationScreen(Screen.HOME.name)
    data object OnBoarding : NavigationScreen(Screen.ON_BOARDING.name)
    data object Login : NavigationScreen(Screen.LOGIN.name)
    data object Register : NavigationScreen(Screen.REGISTER.name)
    data object ForgotPassword : NavigationScreen(Screen.FORGOT_PASSWORD.name)
    data object Profile : NavigationScreen(Screen.PROFILE.name)
    data object EditProfile : NavigationScreen(Screen.EDIT_PROFILE.name)
    data object ChangePassword : NavigationScreen(Screen.CHANGE_PASSWORD.name)
    data object ChooseWay : NavigationScreen(Screen.CHOOSE_WAY.name)

    sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int) {
        data object Home : BottomNavItem(NavigationScreen.Home.route, R.drawable.ic_home)

    }
}
