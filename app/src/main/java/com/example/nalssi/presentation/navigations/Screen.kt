package com.example.nalssi.presentation.navigations

sealed class Screen (val route: String) {
    object Home: Screen("home")
    object DetailWeather: Screen("detail_weather/{custom_id}")
    object Profile: Screen("profile")
    object Settings: Screen("settings")
    object Favorite: Screen("favorite")
}