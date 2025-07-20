package org.example.rickmortyapp.ui.core.components

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import org.example.rickmortyapp.ui.core.Green
import kotlin.math.max

@Composable
actual fun <T : Any> LazyVerticalGridTarget(
    pagingItems: LazyPagingItems<T>,
    itemContent: @Composable (T) -> Unit,
    additionalContent: @Composable () -> Unit
) {

    val lazyState = rememberLazyGridState()

    Row {
        LazyVerticalGrid(
            state = lazyState,
            columns = GridCells.Adaptive(minSize = 250.dp),
            modifier = Modifier.padding(horizontal = 32.dp).weight(.8f),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if(additionalContent != {}) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.widthIn(max = 500.dp), contentAlignment = Alignment.Center){
                            additionalContent()
                        }
                    }
                }
            }
            items(pagingItems.itemCount) { pos ->
                pagingItems[pos]?.let {
                    itemContent(it)
                }
            }
        }
        VerticalScrollbar(adapter = rememberScrollbarAdapter(lazyState), style = LocalScrollbarStyle.current.copy(
            hoverColor = Green,
            unhoverColor = Green.copy(alpha = .6f)
        ))
    }
}