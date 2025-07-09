package org.example.rickmortyapp.ui.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.rickmortyapp.ui.core.Routes
import org.example.rickmortyapp.ui.home.tabs.characters.CharacterScreen
import org.example.rickmortyapp.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun BottomNavigationWrapper(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.Episodes.route) {
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }
        composable(Routes.Characters.route) {
            CharacterScreen()
        }
    }
}