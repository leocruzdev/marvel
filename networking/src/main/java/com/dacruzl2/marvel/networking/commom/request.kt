package com.dacruzl2.marvel.networking.commom

import com.dacruzl2.marvel.networking.commom.errorhandling.HttpErrorHandling
import com.dacruzl2.marvel.networking.commom.errorhandling.NetworkingErrorHandling
import com.dacruzl2.marvel.networking.commom.errorhandling.SerializationErrorHandling

val exceptionsHandled =
    listOf(
        HttpErrorHandling,
        NetworkingErrorHandling,
        SerializationErrorHandling
    )

suspend fun Throwable.handleError() =
    exceptionsHandled
        .map { it.handling(incoming = this) }
        .reduce { handledException, another ->
            when {
                handledException == another -> handledException
                another == this -> handledException
                else -> another
            }
        }