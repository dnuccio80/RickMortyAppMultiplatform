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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if(additionalContent != {}) {
                            item(span = { GridItemSpan(2) }) {
                                additionalContent()
                            }
                        }
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let {
                                itemContent(it)
                            }
                        }
                    }
                }

                ROW -> {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().height(300.dp).padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let {
                                itemContent(it)
                            }
                        }
                    }
                }
            }

            // Load more content
            if (pagingItems.loadState.append is LoadState.Loading) {
                loadingContent()
            }
        }
    }
}