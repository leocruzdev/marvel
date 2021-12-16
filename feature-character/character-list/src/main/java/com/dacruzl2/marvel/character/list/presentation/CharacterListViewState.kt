package com.dacruzl2.marvel.character.list.presentation

import com.dacruzl2.marvel.character.list.presentation.model.ViewCharacter

internal sealed class CharacterListViewState {

    internal object Loading : CharacterListViewState()

    internal data class Loaded(val value: List<ViewCharacter>) : CharacterListViewState()

    internal object Empty : CharacterListViewState()

    internal data class Error(val cause: Throwable) : CharacterListViewState()
}