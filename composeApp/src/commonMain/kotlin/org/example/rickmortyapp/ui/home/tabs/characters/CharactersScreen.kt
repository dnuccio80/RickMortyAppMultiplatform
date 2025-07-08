package org.example.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterScreen() {

    val viewmodel = koinViewModel<CharactersViewModel>()

    val state by viewmodel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color.Yellow))
    state.characterOfTheDay?.name?.let {
        Text("Character of the day: $it")
    }
}