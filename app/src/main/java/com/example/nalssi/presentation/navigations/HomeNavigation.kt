package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.presentation.callback.HomeScreenCallback
import com.example.nalssi.presentation.screens.HomeScreen

fun NavGraphBuilder.homeNavigation(navController: NavController) {
    composable(route = "home") {
        val homeScreenCallback = object: HomeScreenCallback {
            override fun onItemClicked(weatherItem: WeatherItem) {
                navController.navigate("detail/${weatherItem.q}")
            }

            override fun onFavoriteClicked() {
                navController.navigate("favorite")
            }

            override fun onProfileClicked() {
                navController.navigate("profile")
            }
        }
        HomeScreen(homeScreenCallback = homeScreenCallback)
    }
}