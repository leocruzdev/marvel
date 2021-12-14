package com.dacruzl2.marvel.character.list.presentation

import androidx.lifecycle.ViewModel
import com.dacruzl2.marvel.character.list.domain.usecase.GetCharacters
import com.dacruzl2.marvel.networking.commom.handleError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

internal class CharactersListViewModel(
    private val usecase: GetCharacters
) : ViewModel() {

    fun getCharacters(): Flow<CharacterListViewState> = flow {
        usecase()
            .catch { error ->
                emit(CharacterListViewState.Error(cause = error.handleError()))
            }
            .collect { listCharacters ->
                val state = if (listCharacters.isNotEmpty()) {
                    CharacterListViewState.Loaded(listCharacters)
                } else {
                    CharacterListViewState.Empty
                }
                emit(state)
            }
    }
}