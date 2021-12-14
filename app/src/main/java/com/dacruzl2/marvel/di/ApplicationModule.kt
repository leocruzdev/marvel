package com.dacruzl2.marvel.di

import com.dacruzl2.marvel.navigation.ScreenLinks
import org.koin.dsl.module

val applicationModule by lazy {
    module {
        single {
            ScreenLinks.associations
        }
    }
}