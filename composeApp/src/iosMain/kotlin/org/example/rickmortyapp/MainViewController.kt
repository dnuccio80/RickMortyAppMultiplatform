package org.example.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }