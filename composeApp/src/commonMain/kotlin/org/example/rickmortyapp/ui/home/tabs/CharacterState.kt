package org.example.rickmortyapp.ui.home.tabs

import org.example.rickmortyapp.domain.model.CharacterModel

data class CharacterState(
    val characterOfTheDay:CharacterModel? = null
)
