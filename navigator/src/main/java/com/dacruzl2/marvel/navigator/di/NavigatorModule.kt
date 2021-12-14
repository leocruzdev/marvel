package com.dacruzl2.marvel.navigator.di

import com.dacruzl2.marvel.commom.koin.KoinTags
import com.dacruzl2.marvel.navigator.Navigator
import org.koin.core.qualifier.named
import org.koin.dsl.module

val navigatorModule by lazy {
    module {
        single {
            Navigator(
                host = get(named(name = KoinTags.HOST_ACTIVITY)),
                links = get()
            )
        }
    }
}

