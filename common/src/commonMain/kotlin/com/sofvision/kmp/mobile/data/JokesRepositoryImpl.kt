package com.sofvision.kmp.mobile.data

import com.sofvision.kmp.mobile.data.local.JokesLocalSource
import com.sofvision.kmp.mobile.data.remote.JokesRemoteSource
import com.sofvision.kmp.mobile.domain.repositories.JokesRepository
import com.sofvision.kmp.mobile.presentation.base.ioDispatcher
import kotlinx.coroutines.withContext

class JokesRepositoryImpl(
    private val jokesRemoteSource: JokesRemoteSource,
    private val jokesLocalSource: JokesLocalSource
) : JokesRepository {
    override suspend fun getJokes() = withContext(ioDispatcher) {
        try {
            val jokes = jokesRemoteSource.getJokes()
            jokesLocalSource.saveJokes(jokes)
        } catch (exception: Exception) {
            println("Repository Error: ${exception.message}")
        }
        jokesLocalSource.getJokes()
    }
}