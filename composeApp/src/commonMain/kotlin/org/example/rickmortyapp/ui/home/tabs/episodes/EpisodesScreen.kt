package org.example.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodesScreen(innerPadding: PaddingValues) {

    val viewModel = koinViewModel<EpisodesViewModel>()

    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
}

