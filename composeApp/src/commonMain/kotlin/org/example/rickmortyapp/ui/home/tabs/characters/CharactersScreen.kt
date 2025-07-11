package org.example.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.example.rickmortyapp.core.Logger
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.ui.core.components.TitleItem
import org.example.rickmortyapp.ui.home.tabs.CharacterState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterScreen() {

    val viewmodel = koinViewModel<CharactersViewModel>()

    val state by viewmodel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()

    Logger.i("Damian", characters.itemCount.toString())

    Scaffold(modifier = Modifier.fillMaxSize(),) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = .6f)).padding(innerPadding))
        CharacterGridList(characters, state, innerPadding)
    }
}

@Composable
fun CharacterGridList(
    characters: LazyPagingItems<CharacterModel>,
    state: CharacterState,
    innerPadding: PaddingValues
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item(span = { GridItemSpan(2) }) {
            state.characterOfTheDay?.name?.let {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Character of the Day",
                        textAlign = TextAlign.Center,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.DarkGray,
                    )
                    TitleItem(it)
                }
            }
        }

        item(span = { GridItemSpan(2) }) {
            CharacterOfTheDayCardItem(state)
        }

        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                // Initial Load
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(64.dp),
                            color = Color.Red
                        )
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                item {
                    Text("No results found")
                }
            }

            else -> {
                // Load Content
                items(characters.itemCount) { pos ->
                    characters[pos]?.let {
                        CharacterListItem(it)
                    }
                }

                // More content to load
                if (characters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(64.dp),
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterListItem(characterModel: CharacterModel) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Green)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = characterModel.image,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
            Box(
                modifier = Modifier.fillMaxWidth().height(45.dp)
                    .background(Color.Green.copy(alpha = .6f)), contentAlignment = Alignment.Center
            ) {
                Text(
                    characterModel.name,
                    minLines = 1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun CharacterOfTheDayCardItem(state: CharacterState) {
    Card(
        modifier =
            Modifier.fillMaxWidth()
                .padding(16.dp)
                .height(400.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        if (state.characterOfTheDay == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            AsyncImage(
                model = state.characterOfTheDay?.image,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
        }
    }
}