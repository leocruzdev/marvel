package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.RawUrl
import com.dacruzl2.marvel.character.list.domain.model.DomainUrl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class UrlMapperTest {

    @Test
    fun `given an character list url response get a character list url info`() {
        val rawObject1 = RawUrl(
            type = "detail",
            url = "http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=f197ef4b6cbf165647e2675250c1ce9d"
        )
        val rawObject2 = RawUrl(
            type = "wiki",
            url = "http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=f197ef4b6cbf165647e2675250c1ce9d"
        )

        val rawResponseList = listOf(rawObject1, rawObject2)

        val expected = rawResponseList.map { rawUrl ->
            DomainUrl(url = rawUrl.url, type = rawUrl.type)
        }

        val result = UrlMapper().toDomain(rawUrlList = rawResponseList)

        assertEquals(expected, result)
    }
}