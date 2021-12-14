package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.BaseCharacterAttrs
import com.dacruzl2.marvel.character.list.domain.model.DomainBaseAttrs

internal class BaseCharacterAttrsMapper(private val itemMapper: ItemMapper) {

    fun toDomain(rawBaseCharacterAttrs: BaseCharacterAttrs?): DomainBaseAttrs =
        DomainBaseAttrs(
            available = rawBaseCharacterAttrs?.available,
            collectionURI = rawBaseCharacterAttrs?.collectionURI,
            returned = rawBaseCharacterAttrs?.returned,
            items = rawBaseCharacterAttrs?.items?.let { itemMapper(it) }
        )
}