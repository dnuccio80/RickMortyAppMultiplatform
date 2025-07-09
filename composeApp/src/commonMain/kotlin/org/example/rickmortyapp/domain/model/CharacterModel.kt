package org.example.rickmortyapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id:Int,
    val name:String,
    val image:String
)