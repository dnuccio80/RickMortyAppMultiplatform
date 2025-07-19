package org.example.rickmortyapp.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.CharacterOfTheDayModel
import org.example.rickmortyapp.domain.model.EpisodeModel

interface Repository {
    suspend fun getSingleCharacter(id: Int): CharacterModel
    suspend fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterOfTheDay(): CharacterOfTheDayModel?
    suspend fun addCharacterOfTheDay(characterOfTheDayModel: CharacterOfTheDayModel)
    suspend fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>
    suspend fun getCharacterEpisodes(idList: List<String>): List<EpisodeModel>
}