package org.example.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.example.rickmortyapp.core.Logger
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.ui.core.components.ProgressIndicatorItem
import org.example.rickmortyapp.ui.core.components.TitleItem
import org.example.rickmortyapp.ui.home.tabs.episodes.ContentType
import org.example.rickmortyapp.ui.home.tabs.episodes.PagingWrapper
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterScreen() {

    val viewmodel = koinViewModel<CharactersViewModel>()

    val state by viewmodel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()

    Logger.i("Damian", characters.itemCount.toString())

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = .6f))
                .padding(innerPadding)
        ) {
            CharacterPagingWrapper(characters, state)
        }
    }
}

@Composable
private fun CharacterPagingWrapper(
    characters: LazyPagingItems<CharacterModel>,
    state: CharacterState
) {
    PagingWrapper(
        pagingItems = characters,
        contentType = ContentType.VERTICAL_GRID,
        itemContent = {
            CharacterListItem(it)
        },
        loadingContent = {
            ProgressIndicatorItem()
        },
        emptyContent = {},
        additionalContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CharacterOfTheDayTitle(state)
                CharacterOfTheDayCardItem(state)
            }
        }
    )
}

@Composable
private fun CharacterOfTheDayTitle(state: CharacterState) {
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
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
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
                model = state.characterOfTheDay.image,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
        }
    }
}