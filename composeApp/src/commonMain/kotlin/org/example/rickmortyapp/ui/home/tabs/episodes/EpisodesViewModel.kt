package org.example.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.createPagingSourceLoadResultPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.rickmortyapp.domain.Repository
import org.example.rickmortyapp.ui.home.tabs.EpisodeState

class EpisodesViewModel(private val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow(EpisodeState())
    val state: StateFlow<EpisodeState> = _state

    init {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO)  {
                repository.getAllEpisodes()
            }
            _state.update { it.copy(episodes = result) }
        }
    }



}