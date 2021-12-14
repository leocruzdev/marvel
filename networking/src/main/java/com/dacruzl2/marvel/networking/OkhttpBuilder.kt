package com.dacruzl2.marvel.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkhttpBuilder {

    operator fun invoke() = with(OkHttpClient().newBuilder()) {
        addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }
}