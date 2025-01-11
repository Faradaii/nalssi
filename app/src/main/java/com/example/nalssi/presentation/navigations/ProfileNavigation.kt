package com.example.nalssi.presentation.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nalssi.presentation.callback.ProfileScreenCallback
import com.example.nalssi.presentation.screens.ProfileScreen

fun NavGraphBuilder.profileNavigation(navController: NavController) {
    composable(route = Screen.Profile.route) {
        val profileScreenCallback = object: ProfileScreenCallback {
            override fun onBackClicked() {
                navController.popBackStack()
            }
        }
        ProfileScreen(profileScreenCallback = profileScreenCallback)
    }
}