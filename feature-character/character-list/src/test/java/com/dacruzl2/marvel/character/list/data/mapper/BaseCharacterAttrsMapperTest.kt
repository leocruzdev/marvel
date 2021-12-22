package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.domain.model.DomainBaseAttrs
import com.dacruzl2.marvel.rest.BaseCharacterAttrs
import com.dacruzl2.marvel.rest.RawItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BaseCharacterAttrsMapperTest {

    private val itemMapper = ItemMapper()

    @Test
    fun `given an character base attributes response get a character base attributes info`() {
        val rawObject = BaseCharacterAttrs(
            available = 1,
            collectionURI = "https://gateway.marvel.com/v1/public/characters/1017100/comics",
            returned = 1,
            items = listOf(
                RawItem(
                    name = "Avengers: The Initiative (2007) #14",
                    resourceURI = "https://gateway.marvel.com/v1/public/comics/21366"
                )
            )
        )

        val expected = DomainBaseAttrs(
            available = rawObject.available,
            collectionURI = rawObject.collectionURI,
            returned = rawObject.returned,
            items = rawObject.items?.let { itemMapper(it) }
        )

        val result = BaseCharacterAttrsMapper(itemMapper = itemMapper).toDomain(rawBaseCharacterAttrs = rawObject)

        assertEquals(expected, result)
    }
}