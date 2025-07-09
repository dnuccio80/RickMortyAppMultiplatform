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
    val date: String
) {
    fun toDomain(): CharacterOfTheDayModel {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                name = name,
                image = image
            ),
            date = date
        )
    }
}

