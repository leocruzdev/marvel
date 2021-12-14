package com.dacruzl2.marvel.character.list.domain.usecase


import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import kotlinx.coroutines.flow.Flow

class GetCharacters(private val repository: CharacterRepository) {

     suspend operator fun invoke(): Flow<List<DomainCharacter>> = repository.getCharacters()

}