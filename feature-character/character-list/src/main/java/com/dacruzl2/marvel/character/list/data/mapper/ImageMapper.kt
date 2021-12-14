package com.dacruzl2.marvel.character.list.data.mapper

import com.dacruzl2.marvel.character.list.data.response.RawImage
import com.dacruzl2.marvel.character.list.domain.model.DomainImage

internal class ImageMapper {

    operator fun invoke(rawImage: RawImage?): DomainImage =
        DomainImage(
            path = rawImage?.path,
            extension = rawImage?.extension
        )

    operator fun invoke(rawListImage: List<RawImage>): List<DomainImage> =
        rawListImage.map {
            invoke(rawImage = it)
        }
}