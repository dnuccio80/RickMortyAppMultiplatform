package org.example.rickmortyapp.data

import org.example.rickmortyapp.data.remote.ApiService
import org.example.rickmortyapp.domain.Repository
import org.example.rickmortyapp.domain.model.CharacterModel

class RepositoryImpl(private val apiService: ApiService):Repository {
    override suspend fun getSingleCharacter(id: Int): CharacterModel {
        return apiService.getSingleCharacter(id).toCharacterModel()
    }
}