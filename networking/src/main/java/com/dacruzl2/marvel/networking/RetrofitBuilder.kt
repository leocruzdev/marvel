package com.dacruzl2.marvel.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    inline operator fun <reified T> invoke(baseUrl: String) =
        with(Retrofit.Builder()) {
            baseUrl(baseUrl)
            client(OkhttpBuilder())
            addConverterFactory(GsonConverterFactory.create())
            build().create(T::class.java)
        }
}