package org.example.rickmortyapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.CharacterOfTheDayModel

@Entity
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val date: String,
    val status:Boolean,
    val species:String,
    val origin: String,
    val location: String,
    val episode: String
) {
    fun toDomain(): CharacterOfTheDayModel {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                name = name,
                image = image,
                status = status,
                species = species,
                origin = origin,
                location = location,
                episode = episode
            ),
            date = date
        )
    }
}

