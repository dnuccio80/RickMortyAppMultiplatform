package org.example.rickmortyapp.domain

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke() {
        val randomId = (1..826).random()
        repository.getSingleCharacter(randomId.toString())
    }
}