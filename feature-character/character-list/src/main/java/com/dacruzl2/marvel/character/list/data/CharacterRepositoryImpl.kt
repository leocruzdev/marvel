package com.dacruzl2.marvel.character.list.data

import com.dacruzl2.marvel.character.list.BuildConfig
import com.dacruzl2.marvel.character.list.data.mapper.CharacterMapper
import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import com.dacruzl2.marvel.networking.commom.buildMd5AuthParameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class CharacterRepositoryImpl(
    private val service: CharactersService,
    private val mapper: CharacterMapper
) : CharacterRepository {
    private val timeStamp by lazy { System.currentTimeMillis() }

    override suspend fun getCharacters(): Flow<List<DomainCharacter>> {
        val result = service.getCharacters(
            publicKey = BuildConfig.PUBLIC_KEY,
            md5Digest = buildMd5AuthParameter(
                timeStamp,
                BuildConfig.PRIVATE_KEY,
                BuildConfig.PUBLIC_KEY
            ),
            timestamp = timeStamp
        ).let { resultRaw -> mapper.toDomain(resultRaw) }

        return flowOf(result)
    }
}