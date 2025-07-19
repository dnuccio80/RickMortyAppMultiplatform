package org.example.rickmortyapp.ui.home.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import org.example.rickmortyapp.domain.Repository

class CharactersViewModel(private val getCharacterOfTheDayUseCase: GetCharacterOfTheDayUseCase, private val repository: Repository):ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> = _state

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getCharacterOfTheDayUseCase()
            }
            _state.update { it.copy(characterOfTheDay = result) }
        }

        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(characters = repository.getAllCharacters().cachedIn(viewModelScope)) }
        }
    }
}