package com.dacruzl2.marvel.character.list.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = blue700,
    primaryVariant = blue900,
    secondary = pink300,
    onSecondary = blueGray400
)

private val LightColorPalette = lightColors(
    primary = blue700,
    primaryVariant = blue900,
    secondary = pink300,
    onSecondary = blueGray400
)

/**
 * Alkaa main theme.
 *
 * @param darkTheme indicates if the application is in dark theme mode.
 * @param content composable function
 */
@Composable
fun MarvelTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) MarvelColors() else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = MarvelTypography(),
        shapes = shapes,
        content = content
    )
}
