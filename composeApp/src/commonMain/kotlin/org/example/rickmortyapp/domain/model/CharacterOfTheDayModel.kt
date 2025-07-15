package org.example.rickmortyapp.domain.model

import org.example.rickmortyapp.data.local.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val date: String
) {
    fun toEntity(): CharacterOfTheDayEntity {
        return CharacterOfTheDayEntity(
            id = characterModel.id,
            name = characterModel.name,
            image = characterModel.image,
            date = date,
            status = characterModel.status,
            species = characterModel.species,
            origin = characterModel.origin,
            location = characterModel.location,
            episode = characterModel.episode
        )
    }
}
