package com.dacruzl2.marvel.character.list.data.response

import com.google.gson.annotations.SerializedName

data class RawImage(
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("path")
    val path: String?
)
