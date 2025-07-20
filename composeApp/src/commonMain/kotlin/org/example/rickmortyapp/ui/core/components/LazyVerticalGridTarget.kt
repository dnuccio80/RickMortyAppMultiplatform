package org.example.rickmortyapp.ui.core.components

import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
expect fun <T : Any> LazyVerticalGridTarget(
    pagingItems: LazyPagingItems<T>,
    itemContent: @Composable (T) -> Unit,
    additionalContent: @Composable () -> Unit = {}
)