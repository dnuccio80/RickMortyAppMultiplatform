package org.example.rickmortyapp.ui.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundPrimaryColor
    @Composable
    get() = if(isSystemInDarkTheme()) PrimaryBlack.copy(alpha = .8f) else PrimaryWhite

val BackgroundSecondaryColor
    @Composable
    get() = if(isSystemInDarkTheme()) SecondaryBlack else PrimaryWhite

val BackgroundTertiaryColor
    @Composable
    get() = if(isSystemInDarkTheme()) TertiaryBlack else TertiaryWhite

val DefaultTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5ccf92)

val PrimaryWhite = Color(0xFFFFFFFF)
val SecondaryWhite = Color(0xFFEAE8EF)
val TertiaryWhite = Color(0xFFFAFAFA)

val PrimaryBlack = Color(0xFF000000)
val SecondaryBlack = Color(0xFF302F2F)
val TertiaryBlack = Color(0xFF464646)