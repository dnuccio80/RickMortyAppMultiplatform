package org.example.rickmortyapp.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.CharacterOfTheDayModel

interface Repository {
    suspend fun getSingleCharacter(id:Int): CharacterModel
    fun getAllCharacters():Flow<PagingData<CharacterModel>>
    suspend fun getCharacterOfTheDay():CharacterOfTheDayModel?
    fun addCharacterOfTheDay(characterOfTheDayModel: CharacterOfTheDayModel)
}