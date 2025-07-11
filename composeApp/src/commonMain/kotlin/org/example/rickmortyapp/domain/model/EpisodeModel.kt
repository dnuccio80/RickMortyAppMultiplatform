package org.example.rickmortyapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeModel(
    val id:Int,
    val name:String,
    val episode:String,
    val season: SeasonNumber,
    val url:String,
)

enum class SeasonNumber {
    SEASON_1,
    SEASON_2,
    SEASON_3,
    SEASON_4,
    SEASON_5,
    SEASON_6,
    SEASON_7,
    SEASON_8,
    UNKNOWN
}
