package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.domain.model.DomainItem
import com.dacruzl2.marvel.rest.RawItem
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ItemMapperTest {

    @Test
    fun `given an character item response get a character item info`() {
        val rawItemResponse = RawItem(
            name = "Avengers: The Initiative (2007) #14",
            resourceURI = "http://gateway.marvel.com/v1/public/comics/21366"
        )

        val expected = DomainItem(
            name = rawItemResponse.name,
            resourceURI = rawItemResponse.resourceURI
        )

        val result = ItemMapper().invoke(rawItem = rawItemResponse)

        assertEquals(expected, result)
    }

    @Test
    fun `given an character list item response get a character list item info`() {
        val rawObject1 = RawItem(
            name = "Avengers: The Initiative (2007) #14",
            resourceURI = "http://gateway.marvel.com/v1/public/comics/21366"
        )
        val rawObject2 = RawItem(
            name = "Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)",
            resourceURI = "http://gateway.marvel.com/v1/public/comics/24571"
        )

        val rawListItemResponse = listOf(rawObject1, rawObject2)

        val expected = rawListItemResponse.map { rawItem ->
            DomainItem(name = rawItem.name, resourceURI = rawItem.resourceURI)
        }

        val result = ItemMapper().invoke(rawListItem = rawListItemResponse)

        assertEquals(expected, result)
    }
}