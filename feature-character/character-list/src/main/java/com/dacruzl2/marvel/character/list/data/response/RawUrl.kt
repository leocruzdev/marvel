package com.dacruzl2.marvel.character.list.data.response

import com.google.gson.annotations.SerializedName

data class RawUrl(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)