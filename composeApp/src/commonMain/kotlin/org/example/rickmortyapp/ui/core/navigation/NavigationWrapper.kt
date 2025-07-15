package org.example.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.json.Json
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.ui.core.CharacterDetail
import org.example.rickmortyapp.ui.core.Routes
import org.example.rickmortyapp.ui.details.CharacterDetailsScreen
import org.example.rickmortyapp.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(mainNavController)
        }

        composable<CharacterDetail> { navBackStackEntry ->
            val characterDetailEncode: CharacterDetail = navBackStackEntry.toRoute<CharacterDetail>()
            val characterModel: CharacterModel = Json.decodeFromString<CharacterModel>(characterDetailEncode.characterModel)
            CharacterDetailsScreen(characterModel) }
    }
}