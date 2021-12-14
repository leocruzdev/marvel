package com.dacruzl2.marvel.character.list.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

object MarvelColors {
    private val primaryColor = Color(0xFF424242)
    private val primaryLightColor = Color(0xFF6D6D6D)
    private val primaryDarkColor = Color(0xFF1B1B1B)

    private val secondaryColor = Color(0xFFEC1D24)
    private val secondaryLightColor = Color(0xFFFF5F4E)
    private val secondaryDarkColor = Color(0xFFB10000)
    private val primaryTextColor = Color(0xFFFFFFFF)
    private val secondaryTextColor = Color(0xFF000000)

    operator fun invoke() =
        darkColors(
            primary = primaryColor,
            primaryVariant = primaryDarkColor,
            secondary = secondaryColor,
            secondaryVariant = secondaryDarkColor,
            background = primaryLightColor,
            surface = primaryLightColor
        )
}


