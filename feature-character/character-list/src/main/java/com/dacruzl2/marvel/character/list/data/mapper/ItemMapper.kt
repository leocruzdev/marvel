package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.RawItem
import com.dacruzl2.marvel.character.list.domain.model.DomainItem

internal class ItemMapper {

    operator fun invoke(rawItem: RawItem): DomainItem =
        DomainItem(
            name = rawItem.name,
            resourceURI = rawItem.resourceURI
        )

    operator fun invoke(rawListComic: List<RawItem>): List<DomainItem> =
        rawListComic.map {
            invoke(it)
        }
}