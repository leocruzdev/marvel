package com.dacruzl2.marvel.networking.commom.errorhandling

import com.dacruzl2.marvel.networking.commom.errors.RemoteIntegrationError
import com.google.gson.JsonSyntaxException

object SerializationErrorHandling : ErrorHandler {

    override suspend fun handling(incoming: Throwable) =
        when (incoming) {
            is JsonSyntaxException -> RemoteIntegrationError.UnexpectedResponse
            else -> incoming
        }
}