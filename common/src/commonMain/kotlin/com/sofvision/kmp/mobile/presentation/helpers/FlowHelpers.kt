package com.sofvision.kmp.mobile.presentation.helpers

import com.sofvision.kmp.mobile.presentation.base.mainDispatcher
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*


@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun <T> ConflatedBroadcastChannel<T>.asCommonFlow(): CFlow<T> = CFlow(asFlow())

fun <T> Flow<T>.asCommonFlow(): CFlow<T> = CFlow(this)

@OptIn(ExperimentalCoroutinesApi::class)
class CFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(mainDispatcher + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}