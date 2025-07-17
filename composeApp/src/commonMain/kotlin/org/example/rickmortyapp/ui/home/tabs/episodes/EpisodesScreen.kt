package org.example.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import org.example.rickmortyapp.domain.model.EpisodeModel
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_1
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_2
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_3
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_4
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_5
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_6
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_7
import org.example.rickmortyapp.domain.model.SeasonNumber.SEASON_8
import org.example.rickmortyapp.domain.model.SeasonNumber.UNKNOWN
import org.example.rickmortyapp.ui.core.BackgroundPrimaryColor
import org.example.rickmortyapp.ui.core.Green
import org.example.rickmortyapp.ui.core.components.ProgressIndicatorItem
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.season_1
import rickmortyapp.composeapp.generated.resources.season_2
import rickmortyapp.composeapp.generated.resources.season_3
import rickmortyapp.composeapp.generated.resources.season_4
import rickmortyapp.composeapp.generated.resources.season_5
import rickmortyapp.composeapp.generated.resources.season_6
import rickmortyapp.composeapp.generated.resources.season_7
import rickmortyapp.composeapp.generated.resources.season_8


@Composable
fun EpisodesScreen() {

    val viewModel = koinViewModel<EpisodesViewModel>()

    val state by viewModel.state.collectAsState()
    val episodes = state.episodes.collectAsLazyPagingItems()

//    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)) {
            PagingWrapper(
                pagingItems = episodes,
                contentType = ContentType.ROW,
                itemContent = {
                    EpisodeListItem(it)
                },
                loadingContent = {
                    ProgressIndicatorItem()
                }
            )
        }
//    }
}

@Composable
fun EpisodeListItem(episode: EpisodeModel) {

    val image = when (episode.season) {
        SEASON_1 -> painterResource(Res.drawable.season_1)
        SEASON_2 -> painterResource(Res.drawable.season_2)
        SEASON_3 -> painterResource(Res.drawable.season_3)
        SEASON_4 -> painterResource(Res.drawable.season_4)
        SEASON_5 -> painterResource(Res.drawable.season_5)
        SEASON_6 -> painterResource(Res.drawable.season_6)
        SEASON_7 -> painterResource(Res.drawable.season_7)
        SEASON_8 -> painterResource(Res.drawable.season_8)
        UNKNOWN -> painterResource(Res.drawable.season_1)
    }

    Card(
        modifier = Modifier.width(200.dp).fillMaxHeight().clickable {
        },
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Green.copy(alpha = .6f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                image,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(listOf(
                    Color.Transparent,
                    Color.Black.copy(alpha = .7f),
                ))
            ))
            Box(
                modifier = Modifier.fillMaxWidth().height(60.dp)
                    .background(Green.copy(alpha = .6f))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Season ${
                            episode.season.toString().substringAfter("_")
                        } | Episode ${episode.episode.substringAfter("E")}",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        episode.name,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                        minLines = 1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

