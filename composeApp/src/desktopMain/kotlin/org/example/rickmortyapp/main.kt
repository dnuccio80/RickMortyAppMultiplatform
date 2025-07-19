package org.example.rickmortyapp

import androidx.compose.material3.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.rickmortyapp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and Morty App"
    ) {
        initKoin()
        App()
    }
}