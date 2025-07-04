package org.example.rickmortyapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.rickmortyapp.domain.model.CharacterModel

@Serializable
data class CharacterResponse(
    @SerialName("id") val id:String,
    val name:String,
    val image:String
) {
    fun toCharacterModel(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            image = image
        )
    }
}