package org.example.rickmortyapp.data.remote.response

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import org.example.rickmortyapp.domain.model.EpisodeModel
import org.example.rickmortyapp.domain.model.SeasonNumber

@Serializable
data class EpisodeResponse(
    @PrimaryKey
    val id:Int,
    val name:String,
    val episode:String,
) {
    fun toDomain():EpisodeModel {
        val seasonNumber = getSeason(episode)

        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            season = seasonNumber,
            url = getUrl(seasonNumber)
        )
    }

    private fun getSeason(episode: String):SeasonNumber {
        return when(episode.substringBefore("E")) {
            "S01" -> SeasonNumber.SEASON_1
            "S02" -> SeasonNumber.SEASON_2
            "S03" -> SeasonNumber.SEASON_3
            "S04" -> SeasonNumber.SEASON_4
            "S05" -> SeasonNumber.SEASON_5
            "S06" -> SeasonNumber.SEASON_6
            "S07" -> SeasonNumber.SEASON_7
            "S08" -> SeasonNumber.SEASON_8
            else -> SeasonNumber.UNKNOWN
        }
    }

    private fun getUrl(seasonNumber: SeasonNumber):String {
        return when (seasonNumber) {
            SeasonNumber.SEASON_1 -> "https://www.youtube.com/watch?v=8BEzj2kRjO8&ab_channel=RottenTomatoesTV"
            SeasonNumber.SEASON_2 -> "https://www.youtube.com/watch?v=SXwf_9xJu5c&ab_channel=Yusuto"
            SeasonNumber.SEASON_3 -> "https://www.youtube.com/watch?v=Bmg2vXOQ3kM&ab_channel=SeriesTrailerMP"
            SeasonNumber.SEASON_4 -> "https://www.youtube.com/watch?v=bLI2-v264No&ab_channel=RottenTomatoesTV"
            SeasonNumber.SEASON_5 -> "https://www.youtube.com/watch?v=yC1UxW8vcDo&ab_channel=RottenTomatoesTV"
            SeasonNumber.SEASON_6 -> "https://www.youtube.com/watch?v=jerFRSQW9g8&ab_channel=RottenTomatoesTV"
            SeasonNumber.SEASON_7 -> "https://www.youtube.com/watch?v=PkZtVBNkmso&ab_channel=RottenTomatoesTV"
            SeasonNumber.SEASON_8 -> "https://www.youtube.com/watch?v=_AmndYw8WGQ"
            SeasonNumber.UNKNOWN -> ""
        }
    }
}

