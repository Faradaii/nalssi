package com.example.nalssi.presentation.navigations

sealed class Screen (val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Favorite: Screen("favorite")
    object Splash: Screen("splash")
    object DetailWeather: Screen("detail/{q}") {
        fun createRoute(q: String?): String = "detail/$q"
    }
}