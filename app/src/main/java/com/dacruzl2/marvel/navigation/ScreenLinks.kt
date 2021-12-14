package com.dacruzl2.marvel.navigation

import android.app.Activity
import com.dacruzl2.marvel.character.list.presentation.CharactersListActivity
import com.dacruzl2.marvel.navigator.Screen

object ScreenLinks {
    val associations by lazy {
        mapOf<Screen, Class<out Activity>>(
            Screen.CharactersList to CharactersListActivity::class.java
        )
    }
}