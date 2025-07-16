package org.example.rickmortyapp.ui.core.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.rickmortyapp.ui.core.Green

fun Modifier.aliveBorder(isAlive: Boolean): Modifier {
    val color = if (isAlive) Green else Color.Red

    return border(4.dp, color, shape = CircleShape)
}