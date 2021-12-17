package com.dacruzl2.marvel.character.list.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryDarkColor,
    primaryVariant = primaryColor,
    secondary = secondaryDarkColor,
    onSecondary = primaryTextColor
)

private val LightColorPalette = lightColors(
    primary = primaryLightColor,
    primaryVariant = secondaryColor,
    secondary = secondaryLightColor,
    onSecondary = secondaryTextColor
)

@Composable
fun MarvelTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = MarvelTypography(),
        shapes = shapes,
        content = content
    )
}