package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.RawUrl
import com.dacruzl2.marvel.character.list.domain.model.DomainUrl

internal class UrlMapper {

     fun toDomain(rawUrlList: List<RawUrl>): List<DomainUrl> =
        rawUrlList.map {
            DomainUrl(
                type = it.type,
                url = it.url
            )
        }
}