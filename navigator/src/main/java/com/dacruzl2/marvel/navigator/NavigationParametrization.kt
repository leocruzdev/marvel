package com.dacruzl2.marvel.navigator

import android.os.Bundle

data class NavigationParametrization(
    val to: Screen,
    val mode: NavigationMode = NavigationMode.OnlyMove,
    val sending: Bundle? = null,
)