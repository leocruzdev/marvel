package com.dacruzl2.marvel.character.list.presentation.model

@JvmInline
value class ViewImage(val url: String?) {

    operator fun invoke() = url
}