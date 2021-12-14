package com.dacruzl2.marvel.character.list.domain.model

data class DomainCharacter(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val resourceURI: String? = null,
    val thumbnail: DomainImage? = null,
    val urls: List<DomainUrl>? = null,
    val stories: DomainStories? = null,
    val events: DomainEvents? = null,
    val comics: DomainComics? = null,
    val series: DomainSeries? = null
)