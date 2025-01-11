package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.presentation.callback.FavoriteScreenCallback
import com.example.nalssi.presentation.screens.FavoriteScreen

fun NavGraphBuilder.favoriteNavigation(navController: NavController) {
    composable(route = Screen.Favorite.route) {
        val favoriteScreenCallback = object: FavoriteScreenCallback {
            override fun onItemClicked(weatherItem: WeatherItem) {
                navController.navigate(Screen.DetailWeather.createRoute("${ weatherItem.location?.name }-${ weatherItem.location?.region }"))
            }

            override fun onBackClicked() {
                navController.popBackStack()
            }
        }
        FavoriteScreen(favoriteScreenCallback = favoriteScreenCallback)
    }
}