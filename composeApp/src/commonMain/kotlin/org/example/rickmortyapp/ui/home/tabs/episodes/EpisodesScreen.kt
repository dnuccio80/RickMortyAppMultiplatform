package org.example.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import org.example.rickmortyapp.core.Logger
import org.example.rickmortyapp.domain.model.EpisodeModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun EpisodesScreen() {

    val viewModel = koinViewModel<EpisodesViewModel>()

    val state by viewModel.state.collectAsState()
    val episodes = state.episodes.collectAsLazyPagingItems()

    Logger.i("Damian", episodes.itemCount.toString())

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when {
                // First Loading
                episodes.loadState.refresh is LoadState.Loading && episodes.itemCount == 0 -> {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(64.dp),
                                color = Color.Green
                            )
                        }
                    }
                }

                episodes.loadState.refresh is LoadState.NotLoading && episodes.itemCount == 0 -> {
                    item {
                        Text("No Results Found")
                    }
                }

                else -> {
                    // Load Content
                    items(episodes.itemCount) { pos ->
                        episodes[pos]?.let {
                            EpisodeListItem(it)
                        }
                    }

                    // More content to load
                    if (episodes.loadState.append is LoadState.Loading) {
                        item(span = { GridItemSpan(2) }) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(64.dp),
                                    color = Color.Green
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeListItem(episode: EpisodeModel) {
    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp).clickable {

        },
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Green.copy(alpha = .6f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = episode.name,
                    color = Color.Green.copy(alpha = .8f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    text = episode.season.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

