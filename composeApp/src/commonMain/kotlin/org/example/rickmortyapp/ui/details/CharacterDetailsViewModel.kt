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
import org.example.rickmortyapp.domain.GetMultipleEpisodesUseCase
import org.example.rickmortyapp.domain.model.CharacterModel

class CharacterDetailsViewModel(
    characterModel: CharacterModel,
    private val getMultipleEpisodesUseCase: GetMultipleEpisodesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState(characterModel))
    val state: StateFlow<CharacterDetailsState> = _state

    init {
        val episodesDecoded = Json.decodeFromString<List<String>>(characterModel.episode)
        viewModelScope.launch {
            val episodeList = withContext(Dispatchers.IO) {
                getMultipleEpisodesUseCase(episodesDecoded)
            }
            _state.update { it.copy(episodes = episodeList) }
        }
    }
}