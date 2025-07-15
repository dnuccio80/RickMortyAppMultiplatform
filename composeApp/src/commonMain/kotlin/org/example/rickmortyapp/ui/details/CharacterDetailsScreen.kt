package org.example.rickmortyapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.rickmortyapp.domain.model.CharacterModel
import org.example.rickmortyapp.domain.model.EpisodeModel
import org.example.rickmortyapp.ui.core.extensions.aliveBorder
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parameterSetOf
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.star_background

@Composable
fun CharacterDetailsScreen(characterModel: CharacterModel) {

    val characterDetailViewModel =
        koinViewModel<CharacterDetailsViewModel>(parameters = { parameterSetOf(characterModel) })

    val state by characterDetailViewModel.state.collectAsState()
    val character = state.characterModel
    val episodes = state.episodes

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        MainHeader(character)
        Body(character, episodes)
    }
}

@Composable
private fun Body(character: CharacterModel, episodes: List<EpisodeModel>) {

    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "ABOUT THE CHARACTER",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            // USE CHARACTER origin.name
            RowDescriptionItem("Origin", character.origin)
            RowDescriptionItem("Location", character.location)
        }
    }

    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            "EPISODES LIST",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Column(
            modifier = Modifier.padding(16.dp).heightIn(max = 300.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            episodes.forEach {
                ColumnDescriptionItem(title = it.name, description = it.episode)
            }
        }
    }
}

@Composable
private fun RowDescriptionItem(title: String, description: String) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            "${title}:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            description,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ColumnDescriptionItem(title: String, description: String) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.Green)
        Text(description, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
    }
}

@Composable
fun MainHeader(character: CharacterModel) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.star_background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CharacterHeader(character)
    }
}

@Composable
fun CharacterHeader(character: CharacterModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                character.name,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Specie: ${character.species}", color = Color.Black)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier.size(205.dp).clip(CircleShape)
                        .background(Color.Black.copy(alpha = .15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = character.image,
                        modifier = Modifier.size(190.dp).clip(CircleShape)
                            .aliveBorder(character.status),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
                val aliveStatus = if (character.status) "ALIVE" else "DEAD"
                val backgroundColor = if (character.status) Color.Green else Color.Red
                Text(
                    aliveStatus, color = Color.White, fontSize = 16.sp, modifier = Modifier.clip(
                        RoundedCornerShape(30.dp)
                    ).background(backgroundColor).padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }
    }
}
