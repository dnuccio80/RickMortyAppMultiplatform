package org.example.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.rickmortyapp.ui.core.Routes
import org.example.rickmortyapp.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()


    NavHost(navController = mainNavController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}