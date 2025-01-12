package com.example.nalssi

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nalssi.presentation.navigations.AppNavigation

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
    ) { _ ->
        AppNavigation(navController = navController)
    }

}