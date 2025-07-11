package org.example.rickmortyapp.ui.home.tabs

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.Serializable
import org.example.rickmortyapp.domain.model.EpisodeModel

data class EpisodeState(
    val episodes:Flow<PagingData<EpisodeModel>> = emptyFlow()
)