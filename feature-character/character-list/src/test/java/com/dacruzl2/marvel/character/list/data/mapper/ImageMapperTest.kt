package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.RawImage
import com.dacruzl2.marvel.character.list.domain.model.DomainImage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ImageMapperTest {

    @Test
    fun `given an character image response get a character image info`() {
        val rawImageResponse = RawImage(path = "https://teste.com.br/image", extension = ".jpg")

        val expected = DomainImage(
            path = "https://teste.com.br/image",
            extension = ".jpg"
        )

        val result = ImageMapper().invoke(rawImage = rawImageResponse)

        assertEquals(expected, result)
    }

    @Test
    fun `given an list of character image response get a list character image info`() {
        val rawObject1 = RawImage(path = "https://teste.com.br/image1", extension = ".jpg")
        val rawObject2 = RawImage(path = "https://teste.com.br/image2", extension = ".jpg")
        val rawObject3 = RawImage(path = "https://teste.com.br/image3", extension = ".jpg")

        val rawListImageResponse = listOf(rawObject1, rawObject2, rawObject3)

        val domainObject1 = DomainImage(path = "https://teste.com.br/image1", extension = ".jpg")
        val domainObject2 = DomainImage(path = "https://teste.com.br/image2", extension = ".jpg")
        val domainObject3 = DomainImage(path = "https://teste.com.br/image3", extension = ".jpg")

        val expected = listOf(domainObject1, domainObject2, domainObject3)

        val result = ImageMapper().invoke(rawListImage = rawListImageResponse)

        assertEquals(expected, result)
    }
}