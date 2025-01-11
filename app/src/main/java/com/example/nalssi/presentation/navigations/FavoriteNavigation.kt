package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.presentation.callback.FavoriteScreenCallback
import com.example.nalssi.presentation.callback.HomeScreenCallback
import com.example.nalssi.presentation.screens.FavoriteScreen
import com.example.nalssi.presentation.screens.HomeScreen

fun NavGraphBuilder.favoriteNavigation(navController: NavController) {
    composable(route = "favorite") {
        val favoriteScreenCallback = object: FavoriteScreenCallback {
            override fun onItemClicked(weatherItem: WeatherItem) {
                navController.navigate("detail/${weatherItem.q}")
            }

            override fun onBackClicked() {
                navController.popBackStack()
            }
        }
        FavoriteScreen(favoriteScreenCallback = favoriteScreenCallback)
    }
}