package org.example.rickmortyapp.ui.home.tabs

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.example.rickmortyapp.domain.model.CharacterModel

data class CharacterState(
    val characterOfTheDay:CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)
