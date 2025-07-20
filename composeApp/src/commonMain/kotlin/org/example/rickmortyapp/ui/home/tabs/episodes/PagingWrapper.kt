package org.example.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import org.example.rickmortyapp.ui.core.components.LazyRowTarget
import org.example.rickmortyapp.ui.core.components.LazyVerticalGridTarget
import org.example.rickmortyapp.ui.home.tabs.episodes.ContentType.*

enum class ContentType {
    VERTICAL_GRID,
    ROW
}


@Composable
fun <T : Any> PagingWrapper(
    pagingItems: LazyPagingItems<T>,
    contentType: ContentType,
    itemContent: @Composable (T) -> Unit,
    loadingContent: @Composable () -> Unit = {},
    emptyContent: @Composable () -> Unit = {},
    additionalContent: @Composable () -> Unit = {}
) {

    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            // Initial loading
            loadingContent()
        }

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            // Not content to show
            emptyContent()
        }

        else -> {
            // Load Content
            when (contentType) {
                VERTICAL_GRID -> {
                    LazyVerticalGridTarget(
                        pagingItems = pagingItems,
                        additionalContent = additionalContent,
                        itemContent = itemContent
                    )
                }

                ROW -> {
                    LazyRowTarget(
                        pagingItems = pagingItems,
                        itemContent = itemContent
                    )
                }
            }

            // Load more content
            if (pagingItems.loadState.append is LoadState.Loading) {
                loadingContent()
            }
        }
    }
}