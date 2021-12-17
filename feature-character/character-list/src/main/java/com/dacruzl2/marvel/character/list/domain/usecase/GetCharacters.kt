package com.dacruzl2.marvel.character.list.domain.usecase

import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import kotlinx.coroutines.flow.map

class GetCharacters(private val repository: CharacterRepository) {

    suspend operator fun invoke() = repository.getCharacters()
        .map { list ->
            list.filter { characterItem ->
                characterItem.thumbnail?.invoke()?.contains("image_not_available")?.not() == true
            }
        }
}