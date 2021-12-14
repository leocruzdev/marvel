package com.dacruzl2.marvel.networking.commom.errorhandling

import com.dacruzl2.marvel.networking.commom.errors.NetworkingError
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkingErrorHandling : ErrorHandler {

    override suspend fun handling(incoming: Throwable) =
        when {
            (!isNetworkingError(incoming)) -> incoming
            isConnectionTimeout(incoming) -> NetworkingError.OperationTimeout
            cannotReachHost(incoming) -> NetworkingError.HostUnreachable
            else -> NetworkingError.ConnectionSpike
        }

    private fun isNetworkingError(error: Throwable) =
        isConnectionTimeout(error) ||
            cannotReachHost(error) ||
            isRequestCanceled(error)

    private fun isRequestCanceled(throwable: Throwable) =
        throwable is IOException &&
            throwable.message?.contentEquals("Canceled") ?: false

    private fun cannotReachHost(error: Throwable) =
        error is UnknownHostException ||
            error is ConnectException ||
            error is NoRouteToHostException

    private fun isConnectionTimeout(error: Throwable) =
        error is SocketTimeoutException
}