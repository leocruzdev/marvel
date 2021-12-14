package com.dacruzl2.marvel.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    inline operator fun <reified T> invoke(apiURL: String, oKhttpClient: OkHttpClient) =
        with(Retrofit.Builder()) {
            baseUrl(apiURL)
            client(oKhttpClient)
            addConverterFactory((GsonConverterFactory.create()))
            build().create(T::class.java)
        }
}