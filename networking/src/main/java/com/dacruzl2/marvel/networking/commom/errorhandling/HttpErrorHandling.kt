package com.dacruzl2.marvel.networking.commom.errorhandling

import com.dacruzl2.marvel.networking.commom.errors.RemoteIntegrationError
import retrofit2.HttpException

object HttpErrorHandling : ErrorHandler {

    override suspend fun handling(incoming: Throwable) =
        when (incoming) {
            is HttpException -> translateUsingStatusCode(incoming.code())
            else -> incoming
        }

    private fun translateUsingStatusCode(code: Int) =
        when (code) {
            404 -> RemoteIntegrationError.Test
            in 400..499 -> RemoteIntegrationError.ClientOrigin
            else -> RemoteIntegrationError.RemoteSystem
        }
}