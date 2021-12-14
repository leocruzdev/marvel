package com.dacruzl2.marvel

import android.app.Application
import com.dacruzl2.marvel.character.list.di.charactersModule
import com.dacruzl2.marvel.di.applicationModule
import com.dacruzl2.marvel.navigator.di.navigatorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MarvelApp)

            modules(
                applicationModule +
                    charactersModule +
                    navigatorModule
            )
        }
    }
}