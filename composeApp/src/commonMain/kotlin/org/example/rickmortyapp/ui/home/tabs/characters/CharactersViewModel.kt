package org.example.rickmortyapp.ui.home.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.rickmortyapp.domain.GetRandomCharacterUseCase
import org.example.rickmortyapp.domain.Repository
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.ui.home.tabs.CharacterState

class CharactersViewModel(private val getRandomCharacterUseCase: GetRandomCharacterUseCase, private val repository: Repository):ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> = _state

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getRandomCharacterUseCase()
            }
            _state.update { it.copy(characterOfTheDay = result) }
        }

        getAllCharacters()
    }

    private fun getAllCharacters() {
        _state.update { it.copy(characters = repository.getAllCharacters()) }
    }

}