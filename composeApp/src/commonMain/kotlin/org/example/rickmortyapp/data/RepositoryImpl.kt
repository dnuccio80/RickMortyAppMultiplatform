package org.example.rickmortyapp.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.rickmortyapp.data.local.database.RickMortyDataBase
import org.example.rickmortyapp.data.remote.ApiService
import org.example.rickmortyapp.data.remote.paging.CharactersPagingSource
import org.example.rickmortyapp.data.remote.paging.EpisodesPagingSource
import org.example.rickmortyapp.domain.Repository
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.CharacterOfTheDayModel
import org.example.rickmortyapp.domain.model.EpisodeModel

class RepositoryImpl(
    private val apiService: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val episodesPagingSource: EpisodesPagingSource,
    private val rickMortyDataBase: RickMortyDataBase
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_DISTANCE = 4
    }

    override suspend fun getSingleCharacter(id: Int): CharacterModel {
        return apiService.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { charactersPagingSource }
        ).flow
    }

    override suspend fun getCharacterOfTheDay(): CharacterOfTheDayModel? {
        if (rickMortyDataBase.getPreferencesDao().getCharacterOfTheDay() != null) {
            return rickMortyDataBase.getPreferencesDao().getCharacterOfTheDay()!!.toDomain()
        }
        return null
    }

    override fun addCharacterOfTheDay(characterOfTheDayModel: CharacterOfTheDayModel) {
        val newCharacter = characterOfTheDayModel.toEntity()
        rickMortyDataBase.getPreferencesDao().addCharacterOfTheDay(
            newCharacter
        )
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { episodesPagingSource }
        ).flow
    }
}