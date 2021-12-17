package com.dacruzl2.marvel.character.list.presentation.mapper

import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter as DomainCharacter
import com.dacruzl2.marvel.character.list.presentation.model.ViewCharacter as ViewCharacter

/**
 * Maps Character between Domain and View.
 */
internal class CharacterMapper(private val viewImageMapper: ViewImageMapper) {

    fun toView(domainCharacterList: List<DomainCharacter>): List<ViewCharacter> =
        domainCharacterList.map { toView(it) }

    private fun toView(domainCharacter: DomainCharacter): ViewCharacter =
        ViewCharacter(
            id = domainCharacter.id,
            name = domainCharacter.name,
            description = domainCharacter.description,
            thumbnail = domainCharacter.thumbnail?.let { viewImageMapper(domainImage = it) }
        )
}