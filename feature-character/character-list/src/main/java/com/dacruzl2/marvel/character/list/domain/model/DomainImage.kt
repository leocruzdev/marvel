package com.dacruzl2.marvel.character.list.domain.model

data class DomainImage(
    val extension: String? = null,
    val path: String? = null
) {
    operator fun invoke(): String = "${path}.${extension}"
}