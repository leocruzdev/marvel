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
            path = rawImageResponse.path,
            extension = rawImageResponse.extension
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

        val expected = rawListImageResponse.map { rawImage ->
            DomainImage(path = rawImage.path, extension = rawImage.extension)
        }

        val result = ImageMapper().invoke(rawListImage = rawListImageResponse)

        assertEquals(expected, result)
    }
}