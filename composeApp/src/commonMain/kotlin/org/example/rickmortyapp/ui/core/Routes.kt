package org.example.rickmortyapp.ui.core

import kotlinx.serialization.Serializable
import org.example.rickmortyapp.domain.model.CharacterModel

sealed class Routes(val route:String) {
    data object Home: Routes("home")

    // Bottom Bar Home Screen
    data object Episodes:Routes("episodes")
    data object Characters:Routes("characters")
}

@Serializable
data class CharacterDetail(val characterModel:String)