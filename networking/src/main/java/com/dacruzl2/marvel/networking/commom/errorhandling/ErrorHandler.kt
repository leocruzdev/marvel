package com.dacruzl2.marvel.networking.commom.errorhandling

interface ErrorHandler {

    suspend fun handling(incoming: Throwable): Throwable
}