package com.dacruzl2.marvel.networking.commom.statemachine

import com.dacruzl2.marvel.networking.commom.exceptionsHandled
import com.dacruzl2.marvel.networking.commom.statemachine.StateMachineEvent.Failure
import com.dacruzl2.marvel.networking.commom.statemachine.StateMachineEvent.Finish
import com.dacruzl2.marvel.networking.commom.statemachine.StateMachineEvent.Start
import com.dacruzl2.marvel.networking.commom.statemachine.StateMachineEvent.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun <T> stateMachine(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    action: suspend () -> T
): Flow<StateMachineEvent<T>> = flow<StateMachineEvent<T>> { emit(Success(action())) }
    .catch { exception ->
        val error = exceptionsHandled
            .map { it.handling(incoming = exception) }
            .reduce { handledException, another ->
                when {
                    handledException == another -> handledException
                    another == exception -> handledException
                    else -> another
                }
            }

        emit(Failure(error))
    }
    .onStart { emit(Start) }
    .onCompletion { emit(Finish) }
    .flowOn(dispatcher)

fun <T> Flow<T>.collectIn(
    scope: CoroutineScope,
    action: suspend (value: T) -> Unit
): Job =
    scope.launch {
        collect(action)
    }