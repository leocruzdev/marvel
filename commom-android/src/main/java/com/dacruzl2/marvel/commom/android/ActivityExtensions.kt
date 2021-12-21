package com.dacruzl2.marvel.commom.android

import androidx.activity.ComponentActivity
import com.dacruzl2.marvel.commom.koin.KoinTags
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun ComponentActivity.selfInject() = koinApplication {
    val hostScreen = module {
        single<ComponentActivity>(named(KoinTags.HOST_ACTIVITY)) {
            this@selfInject
        }
    }
    loadKoinModules(hostScreen)
}