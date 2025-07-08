package org.example.rickmortyapp.domain

import org.example.rickmortyapp.domain.model.CharacterModel

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {
        val randomId = (1..826).random()
        return repository.getSingleCharacter(randomId)
    }
}