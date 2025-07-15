package org.example.rickmortyapp.domain

import org.example.rickmortyapp.domain.model.EpisodeModel

class GetMultipleEpisodesUseCase (private val repository: Repository) {

    suspend operator fun invoke(idList:List<String>): List<EpisodeModel> {
        val idInString = idList.joinToString(",")
        return repository.getMultipleEpisodes(idInString)
    }
}