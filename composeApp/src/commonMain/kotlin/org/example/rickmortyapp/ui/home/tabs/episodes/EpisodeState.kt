package org.example.rickmortyapp.ui.home.tabs.episodes

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.example.rickmortyapp.domain.model.EpisodeModel

data class EpisodeState(
    val episodes:Flow<PagingData<EpisodeModel>> = emptyFlow()
)