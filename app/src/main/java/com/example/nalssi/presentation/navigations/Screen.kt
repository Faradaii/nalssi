package com.example.nalssi.presentation.navigations

sealed class Screen (val route: String) {
    data object Home: Screen("home")
    data object Profile: Screen("profile")
    data object Favorite: Screen("favorite")
    data object Splash: Screen("splash")
    data object DetailWeather: Screen("detail/{q}") {
        fun createRoute(q: String?): String = "detail/$q"
    }
}