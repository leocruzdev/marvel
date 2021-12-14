package com.dacruzl2.marvel.character.list.domain

import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacters(): Flow<List<DomainCharacter>>
}