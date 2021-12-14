package com.dacruzl2.marvel.character.list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dacruzl2.marvel.character.list.theme.MarvelTheme
import com.dacruzl2.marvel.navigator.Navigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListActivity : ComponentActivity() {

    private val navigator by inject<Navigator>()
    private val viewModel by viewModel<CharactersListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelTheme {
                CharactersSection(onUpPress = { finish() })
            }
        }
    }
}