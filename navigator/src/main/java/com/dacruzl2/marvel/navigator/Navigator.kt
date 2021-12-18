package com.dacruzl2.marvel.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class Navigator(
    private val host: FragmentActivity,
    private val links: Map<Screen, Class<out Activity>>
) {

    fun navigate(navigation: NavigationParametrization) = with(navigation) {
        val next = find(to)

        val intent = Intent(host, next).apply {
            when (mode) {
                NavigationMode.Obliterate -> {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    host.finishAndRemoveTask()
                }
                NavigationMode.MoveAndClearPrevious -> {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    host.finish()
                }
                NavigationMode.MoveAndFinishActual -> {
                    host.finish()
                }
                NavigationMode.OnlyMove -> {}
            }

            putParams(sending)
        }

        host.startActivity(intent)
    }

    private fun find(target: Screen) =
        links[target] ?: throw UnsupportedNavigation(target)

    private fun Intent.putParams(params: Bundle?) {
        try {
            params?.let(::putExtras)
        } catch (e: Throwable) {

        }
    }
}