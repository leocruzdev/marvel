package com.dacruzl2.marvel.navigator

data class UnsupportedNavigation(val destination: Screen) : RuntimeException(
    "Cannot navigate to this destination -> $destination"
)
