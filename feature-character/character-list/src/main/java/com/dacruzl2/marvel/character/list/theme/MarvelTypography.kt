package com.dacruzl2.marvel.character.list.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.dacruzl2.marvel.character.list.R

object MarvelTypography {

   private val notoSansFontFamily = FontFamily(Font(R.font.roboto_condensed_bold))

   operator fun invoke() =
       Typography(
           defaultFontFamily = notoSansFontFamily
       )
}
