package org.example.rickmortyapp.domain.model

import kotlinx.serialization.Serializable
import org.example.rickmortyapp.data.local.entity.CharacterOfTheDayEntity

@Serializable
data class CharacterModel(
    val id:Int,
    val name:String,
    val image:String,
)