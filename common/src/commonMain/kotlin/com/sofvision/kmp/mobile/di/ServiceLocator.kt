package com.sofvision.kmp.mobile.di

import com.softvision.kmp.JokesDatabase
import com.sofvision.kmp.mobile.data.JokesRepositoryImpl
import com.sofvision.kmp.mobile.data.local.JokesDatabaseDriver
import com.sofvision.kmp.mobile.data.remote.engine
import com.sofvision.kmp.mobile.data.local.JokesLocalSource
import com.sofvision.kmp.mobile.data.remote.JokesRemoteSource
import com.sofvision.kmp.mobile.domain.repositories.JokesRepository
import com.sofvision.kmp.mobile.domain.usecases.GetJokes
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceLocator {
    private val jokesLocalSource = JokesLocalSource(JokesDatabase(JokesDatabaseDriver.getDriver()))
    private val jokesRemoteSource = JokesRemoteSource(engine)
    private val jokesRepository: JokesRepository = JokesRepositoryImpl(jokesRemoteSource, jokesLocalSource)

    val getJokes = GetJokes(jokesRepository)
}