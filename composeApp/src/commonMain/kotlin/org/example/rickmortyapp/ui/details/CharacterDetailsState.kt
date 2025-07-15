package org.example.rickmortyapp.ui.details

import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.EpisodeModel

data class CharacterDetailsState(
    val characterModel: CharacterModel,
    val episodes:List<EpisodeModel> = emptyList()
)
