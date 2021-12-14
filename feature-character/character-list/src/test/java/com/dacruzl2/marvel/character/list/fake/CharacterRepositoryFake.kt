package com.dacruzl2.marvel.character.list.fake

import com.dacruzl2.marvel.character.list.data.CharactersService
import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class CharacterRepositoryFake(
    private val service: CharactersService
): CharacterRepository {

    override suspend fun getCharacters(): Flow<List<DomainCharacter>> =
        flow {

        }
}