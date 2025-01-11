package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.presentation.callback.DetailScreenCallback
import com.example.nalssi.presentation.screens.DetailScreen

fun NavGraphBuilder.detailNavigation(navController: NavController) {
    composable(route = "detail/{q}") {
        val detailScreenCallback = object: DetailScreenCallback {
            override fun onBackClicked() {
                navController.popBackStack()
            }
        }
        DetailScreen(detailScreenCallback = detailScreenCallback, q = it.arguments?.getString("q") ?: "")
    }
}