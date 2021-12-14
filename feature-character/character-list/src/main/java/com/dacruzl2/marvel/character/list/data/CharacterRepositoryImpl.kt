package com.dacruzl2.marvel.character.list.data

import com.dacruzl2.marvel.character.list.BuildConfig
import com.dacruzl2.marvel.character.list.data.mapper.CharacterMapper
import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import com.dacruzl2.marvel.networking.commom.Util
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

internal class CharacterRepositoryImpl(
    private val service: CharactersService,
    private val mapper: CharacterMapper
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<DomainCharacter>> = flow {
        val timeStamp = measureTimeMillis {}

        val result = service.getCharacters(
            BuildConfig.PUBLIC_KEY,
            Util.buildMd5AuthParameter(
                timeStamp, BuildConfig.PRIVATE_KEY, BuildConfig.PUBLIC_KEY
            ),
            timeStamp,
            null,
            100
        )

        emit(mapper.toDomain(result))
    }
}