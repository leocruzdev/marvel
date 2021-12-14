package com.dacruzl2.marvel.character.list.domain.model

typealias DomainStories = DomainBaseAttrs
typealias DomainEvents = DomainBaseAttrs
typealias DomainComics = DomainBaseAttrs
typealias DomainSeries = DomainBaseAttrs

data class DomainBaseAttrs(
    val available: Int? = null,
    val collectionURI: String? = null,
    val items: List<DomainItem>? = null,
    val returned: Int? = null
)

data class DomainItem(
    val name: String? = null,
    val resourceURI: String? = null
) {

    var type: String? = null

    // For DomainStories
    constructor(type: String?, resourceURI: String, name: String?) : this(name = name, resourceURI = resourceURI) {
        this.type = type
    }
}