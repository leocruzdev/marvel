package com.dacruzl2.marvel.character.list.presentation.mapper

import com.dacruzl2.marvel.character.list.domain.model.DomainImage as DomainImage
import com.dacruzl2.marvel.character.list.presentation.model.ViewImage as ViewImage

/**
 * Maps ImageCharacter between Domain and View.
 */
internal class ViewImageMapper {

    operator fun invoke(domainImage: DomainImage) = ViewImage(url = domainImage())
}