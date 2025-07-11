package org.example.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.example.rickmortyapp.data.remote.ApiService
import org.example.rickmortyapp.domain.model.EpisodeModel

class EpisodesPagingSource(private val apiService: ApiService) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllEpisodes(page)
            val episodes = response.results.map { it.toDomain() }
            val prevPage = if (response.info.prev != null) page - 1 else null
            val nextPage = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = episodes,
                prevKey = prevPage,
                nextKey = nextPage
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}