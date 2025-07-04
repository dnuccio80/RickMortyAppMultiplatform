package org.example.rickmortyapp.ui.core

sealed class Routes(val route:String) {
    data object Home: Routes("home")

    // Bottom Bar Home Screen
    data object Episodes:Routes("episodes")
    data object Characters:Routes("characters")
}