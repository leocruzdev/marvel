package com.dacruzl2.marvel.networking.commom.errors

sealed class RemoteIntegrationError : Throwable() {
    object ClientOrigin : RemoteIntegrationError()
    object RemoteSystem : RemoteIntegrationError()
    object UnexpectedResponse : RemoteIntegrationError()
    object Test: RemoteIntegrationError()

    override fun toString() =
        when (this) {
            ClientOrigin -> "Issue originated from client"
            RemoteSystem -> "Issue incoming from server"
            UnexpectedResponse -> "Broken contract"
            Test -> "testing"
        }
}