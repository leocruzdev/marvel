package com.dacruzl2.marvel.character.list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dacruzl2.marvel.character.list.theme.MarvelTheme

class CharactersListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelTheme {
                CharactersSection(onUpPress = { finish() })
            }
        }
    }
}