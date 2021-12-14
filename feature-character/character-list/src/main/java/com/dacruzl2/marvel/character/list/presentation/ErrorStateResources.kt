package com.dacruzl2.marvel.character.list.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.twotone.Dns
import androidx.compose.material.icons.twotone.SearchOff
import androidx.compose.material.icons.twotone.SignalCellular0Bar
import androidx.compose.ui.graphics.vector.ImageVector
import com.dacruzl2.marvel.character.list.R
import com.dacruzl2.marvel.networking.commom.errors.NetworkingError
import com.dacruzl2.marvel.networking.commom.errors.RemoteIntegrationError

data class ErrorStateResources(
    val image: ImageVector,
    val message: Int
) {
    companion object {
        operator fun invoke(error: Throwable) =
            when (error) {
                is RemoteIntegrationError.RemoteSystem -> ErrorStateResources(
                    image = Icons.TwoTone.Dns,
                    message = R.string.error_server_down
                )
                is NetworkingError -> ErrorStateResources(
                    image = Icons.TwoTone.SignalCellular0Bar,
                    message = R.string.error_network
                )
                is RemoteIntegrationError.Test -> ErrorStateResources(
                    image = Icons.TwoTone.SearchOff,
                    message = R.string.error_no_results
                )
                else -> ErrorStateResources(
                    image = Icons.Outlined.BugReport,
                    message = R.string.error_bug_found
                )
            }
    }
}