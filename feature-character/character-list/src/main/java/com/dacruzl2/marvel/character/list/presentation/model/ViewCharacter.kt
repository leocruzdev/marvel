package com.dacruzl2.marvel.character.list.presentation.model

data class ViewCharacter(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val thumbnail: ViewImage? = null
)