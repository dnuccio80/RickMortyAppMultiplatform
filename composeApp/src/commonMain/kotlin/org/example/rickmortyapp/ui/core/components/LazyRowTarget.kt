package org.example.rickmortyapp.ui.core.components

import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
expect fun <T:Any> LazyRowTarget(pagingItems: LazyPagingItems<T>, itemContent: @Composable (T) -> Unit)