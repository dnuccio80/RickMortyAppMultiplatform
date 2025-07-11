package org.example.rickmortyapp.domain

import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import org.example.rickmortyapp.core.Logger
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class GetCharacterOfTheDayUseCase(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {

        val characterOfTheDay = repository.getCharacterOfTheDay()
        val date = getCurrentDate()

        return if (characterOfTheDay == null || characterOfTheDay.date != date) {
            val newCharacterOfTheDay = getRandomCharacter(repository)
            repository.addCharacterOfTheDay(CharacterOfTheDayModel(newCharacterOfTheDay, date))
            newCharacterOfTheDay
        } else {
            characterOfTheDay.characterModel
        }

    }
}

private suspend fun getRandomCharacter(repository: Repository):CharacterModel {
    val randomId = (1..826).random()
    return repository.getSingleCharacter(randomId)
}

@OptIn(ExperimentalTime::class)
private fun getCurrentDate():String {
    val instant = Clock.System.now()
    val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    val day = localDate.day
    val month = localDate.month.number
    val year = localDate.year

    return "$day/$month/$year"
}