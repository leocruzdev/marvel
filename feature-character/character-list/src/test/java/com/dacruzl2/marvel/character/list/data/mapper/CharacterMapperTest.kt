package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.RawCharacter
import com.dacruzl2.marvel.character.list.data.response.RawImage
import com.dacruzl2.marvel.character.list.data.response.RawUrl
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import com.dacruzl2.marvel.rest.BaseCharacterAttrs
import com.dacruzl2.marvel.rest.BaseRawData
import com.dacruzl2.marvel.rest.BaseRawReponse
import com.dacruzl2.marvel.rest.RawItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CharacterMapperTest {
    private val urlMapper = UrlMapper()
    private val imageMapper = ImageMapper()
    private val itemMapper = ItemMapper()
    private val baseAttrsMapper = BaseCharacterAttrsMapper(itemMapper = itemMapper)

    @Test
    fun `given an root base response character response get a base response character info`() {
        val baseRawResponse = BaseRawReponse(
            attributionHTML = "",
            attributionText = "",
            code = 1,
            copyright = "",
            etag = "",
            mData = BaseRawData(
                offset = 0,
                limit = 2,
                total = 2,
                count = 2,
                results = listOf(
                    RawCharacter(
                        name = "",
                        resourceURI = "",
                        thumbnail = RawImage(extension = "", path = ""),
                        id = 1L,
                        urls = listOf(RawUrl(type = "", url = ""), RawUrl(type = "", url = "")),
                        stories = BaseCharacterAttrs(
                            returned = 0, collectionURI = "", available = 0, items = listOf(
                                RawItem(type = "", resourceURI = "", name = "")
                            )
                        ),
                        series = BaseCharacterAttrs(
                            returned = 0, collectionURI = "", available = 0, items = listOf(
                                RawItem(resourceURI = "", name = "")
                            )
                        ),
                        events = BaseCharacterAttrs(
                            returned = 0, collectionURI = "", available = 0, items = listOf(
                                RawItem(resourceURI = "", name = "")
                            )
                        ),
                        comics = BaseCharacterAttrs(
                            returned = 0, collectionURI = "", available = 0, items = listOf(
                                RawItem(resourceURI = "", name = "")
                            )
                        ),
                        description = "",
                        modified = ""
                    )
                )
            ),
            status = ""
        )

        val rawCharacterResponseList = baseRawResponse.mData?.results

        val expected = rawCharacterResponseList?.map { rawCharacter ->
            DomainCharacter(
                id = rawCharacter.id ?: 0L,
                name = rawCharacter.name.orEmpty(),
                description = rawCharacter.description.orEmpty(),
                thumbnail = rawCharacter.thumbnail?.let { imageMapper(rawImage = it) },
                urls = rawCharacter.urls?.let { urlMapper.toDomain(rawUrlList = it) },
                resourceURI = rawCharacter.resourceURI.orEmpty(),
                modified = rawCharacter.modified.orEmpty(),
                comics = rawCharacter.comics?.let { baseAttrsMapper.toDomain(it) },
                events = rawCharacter.events?.let { baseAttrsMapper.toDomain(it) },
                series = rawCharacter.series?.let { baseAttrsMapper.toDomain(it) },
                stories = rawCharacter.stories?.let { baseAttrsMapper.toDomain(it) }
            )
        }

        val result = CharacterMapper(
            urlMapper = urlMapper,
            imageMapper = imageMapper,
            baseAttrsMapper = baseAttrsMapper
        ).toDomain(rawResponse = baseRawResponse)

        assertEquals(expected, result)
    }

    @Test
    fun `given an character attributes response get a character info`() {
        val rawObject = RawCharacter(
            name = "",
            resourceURI = "",
            thumbnail = RawImage(extension = "", path = ""),
            id = 1L,
            urls = listOf(RawUrl(type = "", url = ""), RawUrl(type = "", url = "")),
            stories = BaseCharacterAttrs(
                returned = 0, collectionURI = "", available = 0, items = listOf(
                    RawItem(type = "", resourceURI = "", name = "")
                )
            ),
            series = BaseCharacterAttrs(
                returned = 0, collectionURI = "", available = 0, items = listOf(
                    RawItem(resourceURI = "", name = "")
                )
            ),
            events = BaseCharacterAttrs(
                returned = 0, collectionURI = "", available = 0, items = listOf(
                    RawItem(resourceURI = "", name = "")
                )
            ),
            comics = BaseCharacterAttrs(
                returned = 0, collectionURI = "", available = 0, items = listOf(
                    RawItem(resourceURI = "", name = "")
                )
            ),
            description = "",
            modified = ""
        )

        val expected = DomainCharacter(
            id = rawObject.id ?: 0L,
            name = rawObject.name.orEmpty(),
            description = rawObject.description.orEmpty(),
            thumbnail = rawObject.thumbnail?.let { imageMapper(rawImage = it) },
            urls = rawObject.urls?.let { urlMapper.toDomain(rawUrlList = it) },
            resourceURI = rawObject.resourceURI.orEmpty(),
            modified = rawObject.modified.orEmpty(),
            comics = rawObject.comics?.let { baseAttrsMapper.toDomain(it) },
            events = rawObject.events?.let { baseAttrsMapper.toDomain(it) },
            series = rawObject.series?.let { baseAttrsMapper.toDomain(it) },
            stories = rawObject.stories?.let { baseAttrsMapper.toDomain(it) }
        )

        val result = CharacterMapper(
            urlMapper = urlMapper,
            imageMapper = imageMapper,
            baseAttrsMapper = baseAttrsMapper
        ).toDomain(rawCharacter = rawObject)

        assertEquals(expected, result)
    }
}