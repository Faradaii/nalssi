package com.example.nalssi.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        homeNavigation(navController = navController)
        detailNavigation(navController = navController)
        profileNavigation(navController = navController)
        favoriteNavigation(navController = navController)
        splashNavigation(navController = navController)
    }
}