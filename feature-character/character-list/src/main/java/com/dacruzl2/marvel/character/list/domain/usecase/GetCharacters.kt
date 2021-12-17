package com.dacruzl2.marvel.character.list.domain.usecase

import com.dacruzl2.marvel.character.list.domain.CharacterRepository

class GetCharacters(private val repository: CharacterRepository) {

    suspend operator fun invoke() = repository.getCharacters()
}