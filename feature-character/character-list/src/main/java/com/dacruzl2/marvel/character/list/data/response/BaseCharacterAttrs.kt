package com.dacruzl2.marvel.character.list.data.response

import com.google.gson.annotations.SerializedName

data class BaseCharacterAttrs(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<RawItem>?,
    @SerializedName("returned")
    val returned: Int?
)

data class RawItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
) {

    @SerializedName("type")
    var type: String? = null

    // For RawStories
    constructor(type: String?, resourceURI: String?, name: String?) : this(name = name, resourceURI = resourceURI) {
        this.type = type
    }
}