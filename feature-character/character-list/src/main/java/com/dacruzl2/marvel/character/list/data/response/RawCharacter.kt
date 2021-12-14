package com.dacruzl2.marvel.character.list.data.response

import com.google.gson.annotations.SerializedName

typealias RawComics = BaseCharacterAttrs
typealias RawSeries = BaseCharacterAttrs
typealias RawEvents = BaseCharacterAttrs
typealias RawStories = BaseCharacterAttrs

data class RawCharacter(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("thumbnail")
    val thumbnail: RawImage?,
    @SerializedName("urls")
    val urls: List<RawUrl>?,
    @SerializedName("stories")
    val stories: RawStories?,
    @SerializedName("events")
    val events: RawEvents?,
    @SerializedName("comics")
    val comics: RawComics?,
    @SerializedName("series")
    val series: RawSeries?
)