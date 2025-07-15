package org.example.rickmortyapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.rickmortyapp.domain.model.CharacterModel

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val origin: OriginResponse,
    val location: LocationResponse,
    val episode: List<String>
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            image = image,
            status = getAliveStatus(status),
            species = species,
            origin = origin.name,
            location = location.name,
            episode = encodeEpisodeList(episode)
        )
    }

    private fun getAliveStatus(state: String): Boolean {
        return state.uppercase() == "ALIVE"
    }

    private fun encodeEpisodeList(episodeList: List<String>): String {
        val subStringList = episodeList.map { episode -> episode.substringAfter("episode/") }
        return Json.encodeToString(subStringList)
    }
}