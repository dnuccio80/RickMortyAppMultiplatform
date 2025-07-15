package org.example.rickmortyapp.domain.model

import kotlinx.serialization.Serializable
import org.example.rickmortyapp.data.local.entity.CharacterOfTheDayEntity
import org.example.rickmortyapp.data.remote.response.LocationResponse
import org.example.rickmortyapp.data.remote.response.OriginResponse

@Serializable
data class CharacterModel(
    val id:Int,
    val name:String,
    val image:String,
    val status:Boolean,
    val species:String,
    val origin: String,
    val location: String,
    val episode: String
)