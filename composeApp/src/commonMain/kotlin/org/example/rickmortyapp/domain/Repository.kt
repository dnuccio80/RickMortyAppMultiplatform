package org.example.rickmortyapp.domain

import org.example.rickmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id:Int): CharacterModel
}