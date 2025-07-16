package org.example.rickmortyapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.example.rickmortyapp.core.Logger
import org.example.rickmortyapp.domain.Repository
import org.example.rickmortyapp.domain.model.CharacterModel

class CharacterDetailsViewModel(
    characterModel: CharacterModel,
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState(characterModel))
    val state: StateFlow<CharacterDetailsState> = _state

    init {
        val episodeFilter = characterModel.episode.substringAfter(",", missingDelimiterValue = "")
        val episodeDecoded = if(episodeFilter.isNotBlank()) Json.decodeFromString<List<String>>(characterModel.episode) else listOf(characterModel.episode)

        viewModelScope.launch {
            val episodes = repository.getCharacterEpisodes(episodeDecoded)
            _state.update { it.copy(episodes = episodes) }
        }
    }
}