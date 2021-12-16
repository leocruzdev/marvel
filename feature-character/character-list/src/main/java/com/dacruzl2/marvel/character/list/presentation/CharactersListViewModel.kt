package com.dacruzl2.marvel.character.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dacruzl2.marvel.character.list.domain.usecase.GetCharacters
import com.dacruzl2.marvel.character.list.presentation.mapper.CharacterMapper
import com.dacruzl2.marvel.networking.commom.handleError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class CharactersListViewModel(
    private val usecase: GetCharacters,
    private val characterMapper: CharacterMapper
) : ViewModel() {

    fun getCharacters(): Flow<CharacterListViewState> = flow {
        usecase()
            .map { character -> characterMapper.toView(character) }
            .catch { error ->
                Log.w("collector", "error: ${error.handleError()}")
                emit(CharacterListViewState.Error(cause = error.handleError()))
            }
            .collect { listCharacters ->
                val state = if (listCharacters.isNotEmpty()) {
                    Log.w("collector", "success: $listCharacters")
                    CharacterListViewState.Loaded(listCharacters)
                } else {
                    Log.w("collector", "empty: $listCharacters")
                    CharacterListViewState.Empty
                }

                Log.w("collector", "final state: $state")
                emit(state)
            }
    }
}