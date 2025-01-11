package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.presentation.callback.ProfileScreenCallback
import com.example.nalssi.presentation.callback.SplashScreenCallback
import com.example.nalssi.presentation.screens.ProfileScreen
import com.example.nalssi.presentation.screens.SplashScreen

fun NavGraphBuilder.splashNavigation(navController: NavController) {
    composable(route = Screen.Splash.route) {
        val splashScreenCallback = object: SplashScreenCallback {
            override fun onRedirected() {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
        SplashScreen(splashScreenCallback = splashScreenCallback)
    }
}