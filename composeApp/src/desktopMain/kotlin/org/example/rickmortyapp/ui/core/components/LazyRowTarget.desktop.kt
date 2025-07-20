package org.example.rickmortyapp.ui.core.components

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import org.example.rickmortyapp.ui.core.Green

@Composable
actual fun <T : Any> LazyRowTarget(
    pagingItems: LazyPagingItems<T>,
    itemContent: @Composable (T) -> Unit
) {

    val lazyState = rememberLazyListState()

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        LazyRow(
            state = lazyState,
            modifier = Modifier.fillMaxWidth().height(300.dp).padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(pagingItems.itemCount) { pos ->
                pagingItems[pos]?.let {
                    itemContent(it)
                }
            }
        }
        HorizontalScrollbar(adapter = rememberScrollbarAdapter(lazyState), style = LocalScrollbarStyle.current.copy(
            unhoverColor = Green.copy(alpha = .6f),
            hoverColor = Green
        ))
    }



}