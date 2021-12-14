package com.dacruzl2.marvel.navigator

sealed class Screen {
    object CharactersList : Screen()
    object CharacterDetail : Screen()

    override fun toString() = when (this) {
        CharactersList -> "Characters Screen"
        CharacterDetail -> "Character Detail Screen"
    }
}