package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.BaseRawReponse
import com.dacruzl2.marvel.character.list.data.response.RawCharacter
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter

internal class CharacterMapper(
    private val urlMapper: UrlMapper,
    private val imageMapper: ImageMapper,
    private val baseAttrsMapper: BaseCharacterAttrsMapper
) {

    fun toDomain(rawResponse: BaseRawReponse<List<RawCharacter>>): List<DomainCharacter> {
        val rawDataContainer = rawResponse.mData
        val rawList = rawDataContainer?.results
        val domainList = rawList?.toMutableList()?.map { toDomain(it) }

        return domainList ?: emptyList()
    }

    private fun toDomain(rawCharacter: RawCharacter?): DomainCharacter =
        DomainCharacter(
            id = rawCharacter?.id ?: 0L,
            name = rawCharacter?.name.orEmpty(),
            description = rawCharacter?.description.orEmpty(),
            thumbnail = rawCharacter?.thumbnail?.let { imageMapper(rawImage = it) },
            urls = rawCharacter?.urls?.let { urlMapper.toDomain(rawUrlList = it) },
            resourceURI = rawCharacter?.resourceURI.orEmpty(),
            modified = rawCharacter?.modified.orEmpty(),
            comics = rawCharacter?.comics?.let { baseAttrsMapper.toDomain(it) },
            events = rawCharacter?.events?.let { baseAttrsMapper.toDomain(it) },
            series = rawCharacter?.series?.let { baseAttrsMapper.toDomain(it) },
            stories = rawCharacter?.stories?.let { baseAttrsMapper.toDomain(it) }
        )
}