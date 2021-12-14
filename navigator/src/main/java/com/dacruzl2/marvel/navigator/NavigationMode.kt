package com.dacruzl2.marvel.navigator

sealed class NavigationMode {
    object OnlyMove : NavigationMode()
    object MoveAndFinishActual : NavigationMode()
    object MoveAndClearPrevious : NavigationMode()
    object Obliterate : NavigationMode()
}