package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.presentation.callback.HomeScreenCallback
import com.example.nalssi.presentation.screens.HomeScreen

fun NavGraphBuilder.homeNavigation(navController: NavController) {
    composable(route = Screen.Home.route) {
        val homeScreenCallback = object: HomeScreenCallback {
            override fun onItemClicked(weatherItem: WeatherItem) {
                navController.navigate(Screen.DetailWeather.createRoute("${ weatherItem.location?.name }-${ weatherItem.location?.region }"))
            }

            override fun onFavoriteClicked() {
                navController.navigate(Screen.Favorite.route)
            }

            override fun onProfileClicked() {
                navController.navigate(Screen.Profile.route)
            }
        }
        HomeScreen(homeScreenCallback = homeScreenCallback)
    }
}