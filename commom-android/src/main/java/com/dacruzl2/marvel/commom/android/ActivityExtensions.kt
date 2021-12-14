package com.dacruzl2.marvel.commom.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.dacruzl2.marvel.commom.koin.KoinTags
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun AppCompatActivity.selfInject() = koinApplication {
    val hostScreen = module {
        single<FragmentActivity>(named(KoinTags.HOST_ACTIVITY)) {
            this@selfInject
        }
    }
    loadKoinModules(hostScreen)
}

